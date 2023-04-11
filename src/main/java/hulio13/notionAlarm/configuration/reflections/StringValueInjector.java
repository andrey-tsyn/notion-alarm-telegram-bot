package hulio13.notionAlarm.configuration.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StringValueInjector {
    public static void injectInField(Field field, String value){
        if (field.getDeclaringClass().isInstance(String.class)){
            field.setAccessible(true);
            try {
                field.set(String.class, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            field.setAccessible(true);
        }
        throw new IllegalArgumentException("Received field is not" +
                " instance of String.");
    }

    public static void injectInMethod(Method method, String value, Class<?> clazz){
        if (method.getParameterCount() != 1)
            throw new IllegalArgumentException("Received method should" +
                    " have 1 parameter");

        Class<?> parameter = method.getParameterTypes()[0];

        if (!parameter.isInstance(String.class))
            throw new IllegalArgumentException("Method parameter should" +
                    "be instance of String.");

        method.setAccessible(true);
        try {
            method.invoke(clazz, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        method.setAccessible(false);
    }
}
