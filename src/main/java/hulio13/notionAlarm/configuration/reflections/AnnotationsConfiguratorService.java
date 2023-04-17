package hulio13.notionAlarm.configuration.reflections;

import hulio13.notionAlarm.configuration.ConfigurationMap;
import hulio13.notionAlarm.configuration.annotations.Configuration;
import hulio13.notionAlarm.configuration.annotations.ConfigureMethod;
import hulio13.notionAlarm.configuration.annotations.Value;
import hulio13.notionAlarm.configuration.exceptions.ConfigureMethodsTooMuchException;
import hulio13.notionAlarm.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationsConfiguratorService {
    private static final Logger logger =
            LoggerFactory.getLogger(AnnotationsConfiguratorService.class);

    public static Object[] getConfiguredConfigurators
            (String configPackage,
             ConfigurationMap map) {
        Set<Class<?>> classes = AnnotationFinder.getClassesWithAnnotation(
                configPackage,
                Configuration.class
        );

        ArrayList<Class<?>> sortedClasses = classes.stream().sorted((o1, o2) -> {
            int order1 = o1.getAnnotation(Configuration.class).order();
            int order2 = o2.getAnnotation(Configuration.class).order();
            return Integer.compare(order1, order2);
        }).collect(Collectors.toCollection(ArrayList::new));

        Object[] objects = new Object[sortedClasses.size()];
        int currIndex = 0;

        for (var clazz : classes) {
            String defaultPath = clazz
                    .getAnnotation(Configuration.class)
                    .defaultPath();

            Constructor<?> constructor;
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                logger.error("'" + clazz.getSimpleName() + "' has no " +
                        "constructor without parameters");
                throw new RuntimeException(e);
            }

            Object instance;
            try {
                instance = constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            objects[currIndex++] = instance;

            initializeValueAnnotatedFieldsAndMethods(
                    instance, map, defaultPath
            );

            callConfigureMethod(clazz, instance);
        }

        return objects;
    }

    private static void initializeValueAnnotatedFieldsAndMethods(
            Object instance, ConfigurationMap map, String initialPath){
        Class<?> clazz = instance.getClass();

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

            StringValueInjector.injectInField(field, valueById, instance);
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
                    instance);
        }
    }

    private static void callConfigureMethod(Class<?> clazz, Object instance){
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
                logger.debug(clazz.getSimpleName() + " configure method executed");
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            method.setAccessible(false);
        } else {
            try {
                Method method = clazz.getDeclaredMethod("configure");
                method.setAccessible(true);
                method.invoke(instance);
                method.setAccessible(false);
            } catch (NoSuchMethodException ignored) { }
            catch (Exception e){
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
