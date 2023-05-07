package hulio13.notionAlarm.telegramBot.inputHandlers.handlers.settings;

import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandlerMarker;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.inputHandlers.utils.StringsToListsConverter;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;

@InputHandlerMarker
public class TokenInputHandler implements InputHandler {
    @Override
    public String getId() {
        return "Token";
    }

    @Override
    public boolean HasNonLocalizableText() {
        return true;
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        switch (input) {
            case "settings.menu" -> properties.setHandlerId("Localization");
            case "settings.change_language" -> properties.setHandlerId("Token");
            case "back" -> properties.setHandlerId("Settings");
        }
    }

    @Override
    public MessageContainer getMessageForUser() {
        MessageContainer messageContainer = new MessageContainer(
                "settings.tokens.menu",
                StringsToListsConverter.convert(
                        2,
                        "Notion",
                        "back")
        );

        return messageContainer;
    }
}
