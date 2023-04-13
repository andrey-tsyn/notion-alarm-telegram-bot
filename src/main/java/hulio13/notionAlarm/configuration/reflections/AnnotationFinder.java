package hulio13.notionAlarm.configuration.reflections;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class AnnotationFinder {
    private static final Logger logger =
            LoggerFactory.getLogger(AnnotationsConfiguratorService.class);

    public static Set<Class<?>> getClassesWithAnnotation(
            String packageName,
            Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> classesAnnotatedWith = reflections.getTypesAnnotatedWith(annotation);

        if (classesAnnotatedWith.size() == 0){
            logger.warn("Zero classes with annotation found. Is package " +
                    packageName + "exist?");
        }

        return classesAnnotatedWith;
    }

    public static Set<Field> getFieldsAnnotatedWith(
            Class<?> clazz,
            Class<? extends Annotation> annotation){

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }

    public static Set<Method> getMethodsAnnotatedWith(
            Class<?> clazz,
            Class<? extends Annotation> annotation){
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(f -> f.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }
}
