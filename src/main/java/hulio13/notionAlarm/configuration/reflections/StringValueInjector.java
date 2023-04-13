package hulio13.notionAlarm.configuration.reflections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StringValueInjector {
    private static final Logger logger =
            LoggerFactory.getLogger(StringValueInjector.class);

    public static void injectInField(Field field, String value, Object instance) {
        if (field.getType().equals(String.class)) {
            field.setAccessible(true);
            try {
                field.set(instance, value);
                logger.trace("Value in field '" + field.getName() + "' of class '" +
                        field.getDeclaringClass().getSimpleName() + "' injected.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            field.setAccessible(false);
        } else throw new IllegalArgumentException("Received field of class '" +
                instance.getClass().getSimpleName() + "' is not" +
                " instance of String.");
    }

    public static void injectInMethod(Method method, String value, Object instance) {
        if (method.getParameterCount() != 1)
            throw new IllegalArgumentException("Received method should" +
                    " have 1 parameter");

        Class<?> parameter = method.getParameterTypes()[0];

        if (!parameter.equals(String.class))
            throw new IllegalArgumentException("Method parameter should" +
                    "be instance of String.");

        method.setAccessible(true);
        try {
            method.invoke(instance, value);
            logger.trace("Value in method '" + method.getName() + "' of class '" +
                    instance.getClass().getSimpleName() + "' injected.");
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        method.setAccessible(false);
    }
}
