package hulio13.notionAlarm.telegramBot.inputHandlers.handlers.wrappers.abstraction;

import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.userInfo.TgUserProperties;

public abstract class InputHandlerWrapper implements InputHandler {
    private InputHandler inputHandler;

    public InputHandlerWrapper(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public String getId() {
        return inputHandler.getId();
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        inputHandler.processInput(input, properties);
    }

    @Override
    public MessageContainer getMessageForUser() {
        return inputHandler.getMessageForUser();
    }

    protected InputHandler getInputHandler(){
        return inputHandler;
    }
}
