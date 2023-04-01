package hulio13.notionAlarm.telegramBot.inputHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class InputHandlersRepository {
    private static final Logger logger =
            LoggerFactory.getLogger(InputHandlersRepository.class);

    private static HashMap<String, InputHandler> inputHandlers = new HashMap<>();

    public static void addHandler(String id, InputHandler inputHandler){
        inputHandlers.put(id, inputHandler);
    }

    public static InputHandler getHandlerById(String id){
        InputHandler handler = inputHandlers.get(id);
        if (handler != null){
            return handler;
        }

        logger.warn("Attempt to access non-existent handler with id: " + id);

        return inputHandlers.get("Default");
    }
}
