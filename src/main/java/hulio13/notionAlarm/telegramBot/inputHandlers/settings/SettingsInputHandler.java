package hulio13.notionAlarm.telegramBot.inputHandlers.settings;

import hulio13.telegramBoot.inputHandlers.InputHandler;
import hulio13.telegramBoot.inputHandlers.InputHandlerMarker;
import hulio13.telegramBoot.inputHandlers.MessageContainer;
import hulio13.telegramBoot.inputHandlers.utils.StringsToListsConverter;
import hulio13.telegramBoot.tgUserProperties.TgUserProperties;

@InputHandlerMarker
public class SettingsInputHandler implements InputHandler {
    @Override
    public String getId() {
        return "Settings";
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        switch (input){
            case "settings.change_language" -> properties.setHandlerId("Localization");
            case "settings.tokens.menu" -> properties.setHandlerId("Token");
            case "back" -> properties.setHandlerId("Default");
        }
    }

    @Override
    public MessageContainer getMessageForUser() {
        MessageContainer messageContainer = new MessageContainer(
                "settings.menu",
                StringsToListsConverter.convert(
                        2,
                        "settings.change_language",
                        "settings.tokens.menu",
                        "back")
        );

        return messageContainer;
    }
}
