package hulio13.notionAlarm.telegramBot.inputHandlers;

import java.util.HashMap;

public class InputHandlersRepository {
    private static HashMap<String, InputHandler> inputHandlers = new HashMap<>();

    public static void addHandler(String id, InputHandler inputHandler){
        inputHandlers.put(id, inputHandler);
    }

    public static InputHandler getHandlerById(String id){
        return inputHandlers.get(id);
    }
}
