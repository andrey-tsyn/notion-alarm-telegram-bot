package hulio13.notionAlarm.telegramBot.inputHandlers.handlers;

import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandlerMarker;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.inputHandlers.utils.StringsToListsConverter;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;

@InputHandlerMarker
public class DefaultInputHandler implements InputHandler {
    @Override
    public String getId() {
        return "Default";
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        switch (input){
            case "settings.menu" -> properties.setHandlerId("Settings");
            case "planned_task.menu" -> properties.setHandlerId("PlannedTask");
        }
    }

    @Override
    public MessageContainer getMessageForUser() {
        MessageContainer messageContainer = new MessageContainer(
                "default",
                StringsToListsConverter.convert(
                        2,
                        "settings.menu",
                        "planned_task.menu")
                );

        return messageContainer;
    }
}
