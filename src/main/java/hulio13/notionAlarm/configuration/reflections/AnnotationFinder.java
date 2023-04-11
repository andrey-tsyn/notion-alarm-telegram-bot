package hulio13.notionAlarm.configuration.reflections;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class AnnotationFinder {
    public static Set<Class<?>> getClassesWithAnnotation(
            String packageName,
            Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(packageName);

        return reflections.getTypesAnnotatedWith(annotation);
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
