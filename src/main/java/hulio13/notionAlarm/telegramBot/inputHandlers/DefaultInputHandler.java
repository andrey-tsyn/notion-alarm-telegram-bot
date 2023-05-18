package hulio13.notionAlarm.telegramBot.inputHandlers;

import hulio13.telegramBoot.inputHandlers.InputHandler;
import hulio13.telegramBoot.inputHandlers.InputHandlerMarker;
import hulio13.telegramBoot.inputHandlers.MessageContainer;
import hulio13.telegramBoot.inputHandlers.utils.StringsToListsConverter;
import hulio13.telegramBoot.tgUserProperties.TgUserProperties;

@InputHandlerMarker
public class DefaultInputHandler implements InputHandler {
    @Override
    public String getId() {
        return "Default";
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        switch (input) {
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
