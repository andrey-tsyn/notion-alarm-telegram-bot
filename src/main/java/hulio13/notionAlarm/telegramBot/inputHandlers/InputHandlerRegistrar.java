package hulio13.notionAlarm.telegramBot.inputHandlers;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public final class InputHandlerRegistrar {
    private static Logger logger = LoggerFactory.getLogger(InputHandlerRegistrar.class);

    public static void registerHandlers(){
        registerWithAnnotations();
    }

    private static void registerWithAnnotations(){
        Reflections reflections = new Reflections("hulio13.notionAlarm.telegramBot");

        Set<Class<?>> set = reflections.getTypesAnnotatedWith(InputHandlerMarker.class);

        for (var clazz : set) {
            try {
                Constructor<?> ctor = clazz.getConstructor();
                InputHandler inputHandler = (InputHandler) ctor.newInstance();
                logger.debug("Input handler with id \"" + inputHandler.getId()
                + "\" added.");
                InputHandlersRepository.addHandler(clazz.getSimpleName(), inputHandler);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                     | InvocationTargetException | IllegalArgumentException e) {
                logger.error("Class " + clazz.getName() + "has not constructor with no args or is not a class.");
                throw new RuntimeException(e);
            }
        }
        logger.info("Input handlers registered.");
    }
}
