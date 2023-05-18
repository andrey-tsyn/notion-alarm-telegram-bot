package hulio13.notionAlarm.configuration.configurators;

import hulio13.configuration.annotations.Configuration;
import hulio13.configuration.annotations.Value;
import hulio13.notionAlarm.telegramBot.wrappers.UserInputHandlerWrapper;
import hulio13.telegramBoot.Bot;
import hulio13.telegramBoot.inputHandlers.InputHandlerRegistrar;
import hulio13.telegramBoot.inputHandlers.InputHandlerWrapperRegistry;
import hulio13.telegramBoot.localization.LanguageRepository;
import hulio13.telegramBoot.localization.LocalizationService;
import hulio13.telegramBoot.localization.json.JsonLanguagesProvider;

@Configuration(defaultPath = "telegram")
public class TelegramConfigurator {
    @Value(path = "botName")
    private String botName;

    @Value(path = "localization.localizationResourceFolder")
    private String localizationResourceFolder;

    @Value(path = "localization.defaultLangTag")
    private String defaultLangTag;

    @Value(path = "token")
    private String token;

    public Bot getConfiguredBot(){
        return new Bot(token, botName);
    }

    public void configure(){
        LocalizationService.init(new LanguageRepository(
                new JsonLanguagesProvider(localizationResourceFolder).getAll(),
                defaultLangTag));

        InputHandlerRegistrar.registerHandlers();

        InputHandlerWrapperRegistry.addWrapper(UserInputHandlerWrapper.class);
    }
}
