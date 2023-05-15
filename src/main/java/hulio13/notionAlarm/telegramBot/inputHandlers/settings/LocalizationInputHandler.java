package hulio13.notionAlarm.telegramBot.inputHandlers.settings;

import hulio13.telegramBoot.inputHandlers.InputHandler;
import hulio13.telegramBoot.inputHandlers.InputHandlerMarker;
import hulio13.telegramBoot.inputHandlers.MessageContainer;
import hulio13.telegramBoot.inputHandlers.utils.StringsToListsConverter;
import hulio13.telegramBoot.localization.LocalizationService;
import hulio13.telegramBoot.tgUserProperties.TgUserProperties;

import java.util.Arrays;

@InputHandlerMarker
public class LocalizationInputHandler implements InputHandler {
    @Override
    public String getId() {
        return "Localization";
    }

    @Override
    public boolean HasNonLocalizableText() {
        return true;
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
