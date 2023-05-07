package hulio13.notionAlarm.telegramBot.inputHandlers.handlers.settings;

import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandlerMarker;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.inputHandlers.utils.StringsToListsConverter;
import hulio13.notionAlarm.telegramBot.localization.LocalizationService;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;

import java.util.Arrays;

@InputHandlerMarker
public class LocalizationInputHandler implements InputHandler {
    @Override
    public String getId() {
        return "Localization";
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        if (Arrays.asList(LocalizationService
                .getAvailableLanguageTags())
                .contains(input)){
            properties.setLocale(input);
            properties.setHandlerId("Default");
        }
    }

    @Override
    public MessageContainer getMessageForUser() {
        return new MessageContainer(
                "Choose your language.\nВыберите ваш язык.",
                StringsToListsConverter.convert(4,
                        LocalizationService.getAvailableLanguageTags())
        );
    }
}
