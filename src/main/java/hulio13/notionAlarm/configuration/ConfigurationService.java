package hulio13.notionAlarm.configuration;

import hulio13.notionAlarm.configuration.annotations.Configuration;
import hulio13.notionAlarm.configuration.annotations.ConfigureMethod;
import hulio13.notionAlarm.configuration.annotations.Value;
import hulio13.notionAlarm.configuration.exceptions.ConfigureMethodsTooMuchException;
import hulio13.notionAlarm.configuration.json.JsonConfigurationFileReader;
import hulio13.notionAlarm.configuration.reflections.AnnotationFinder;
import hulio13.notionAlarm.configuration.reflections.StringValueInjector;
import hulio13.notionAlarm.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public final class ConfigurationService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    public static void configureWithAnnotations(String configPackage)
            throws IOException {
        ConfigurationMap map =
                JsonConfigurationFileReader.readConfigurationFile("config.json");

        Set<Class<?>> classes = AnnotationFinder.getClassesWithAnnotation(
                configPackage,
                Configuration.class
        );

        ArrayList<Class<?>> sortedClasses = classes.stream().sorted((o1, o2) -> {
            int order1 = o1.getAnnotation(Configuration.class).order();
            int order2 = o2.getAnnotation(Configuration.class).order();
            return Integer.compare(order1, order2);
        }).collect(Collectors.toCollection(ArrayList::new));

        for (var clazz : classes) {
            String defaultPath = clazz
                    .getAnnotation(Configuration.class)
                    .defaultPath();

            initializeValueAnnotatedFieldsAndMethods(
                    clazz, map, defaultPath
            );

            callConfigureMethod(clazz);
        }

    }

    private static void initializeValueAnnotatedFieldsAndMethods(
            Class<?> clazz, ConfigurationMap map, String initialPath){
        Set<Field> fields =
                AnnotationFinder.getFieldsAnnotatedWith(clazz, Value.class);
        Set<Method> methods =
                AnnotationFinder.getMethodsAnnotatedWith(clazz, Value.class);

        for (Field field : fields) {
            Value annotation = field.getAnnotation(Value.class);
            String fullPath = concatenateWithDefaultPath(
                    initialPath,
                    annotation.path());
            String valueById = map.getValueById(fullPath);

            if (!annotation.ignoreIfEmpty())
                throwExceptionIfValueIsEmpty(fullPath, valueById);

            StringValueInjector.injectInField(field, valueById);
        }
        
        for (Method method : methods){
            Value annotation = method.getAnnotation(Value.class);
            String fullPath = concatenateWithDefaultPath(
                    initialPath,
                    annotation.path()
            );
            String valueById = map.getValueById(fullPath);

            if (!annotation.ignoreIfEmpty())
                throwExceptionIfValueIsEmpty(fullPath, valueById);

            StringValueInjector.injectInMethod(
                    method,
                    valueById,
                    clazz);
        }
    }

    private static void callConfigureMethod(Class<?> clazz){
        Set<Method> methods = AnnotationFinder.getMethodsAnnotatedWith(
                clazz, ConfigureMethod.class);

        if (methods.size() > 1){
            String message = "Configurator should have one configure method. " +
                    "Class with more than two methods is: " + clazz.getName();

            logger.error(message);
            throw new ConfigureMethodsTooMuchException(message);
        } else if (methods.size() == 1) {
            Method method = (Method) methods.toArray()[0];
            method.setAccessible(true);
            try {
                method.invoke(clazz);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            method.setAccessible(false);
        } else {
            try {
                Method method = clazz.getDeclaredMethod("configure", (Class<?>) null);
                method.setAccessible(true);
                method.invoke(clazz);
                method.setAccessible(false);
            } catch (NoSuchMethodException ignored) { }
            catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void throwExceptionIfValueIsEmpty(
            String fullPath, String valueById) {
        if (valueById == null){
            throw new NotFoundException("'" + fullPath + "' property" +
                    "not found in configuration.");
        }
    }

    private static String concatenateWithDefaultPath(
            String defaultPath, String path){
        if (defaultPath.equals("")) return path;
        return defaultPath + "." + path;
    }
}
