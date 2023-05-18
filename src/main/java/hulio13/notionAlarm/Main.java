package hulio13.notionAlarm;

import hulio13.configuration.ConfiguratorRegistry;
import hulio13.configuration.ConfigurationService;
import hulio13.notionAlarm.configuration.configurators.TelegramConfigurator;
import hulio13.telegramBoot.Bot;
import hulio13.telegramBoot.ServiceRegistry;
import hulio13.telegramBoot.UserIOService;
import hulio13.telegramBoot.localization.LanguageRepository;
import hulio13.telegramBoot.localization.LocalizationService;
import hulio13.telegramBoot.localization.json.JsonLanguagesProvider;
import hulio13.telegramBoot.tgUserProperties.database.TgUserPropertiesRepository;
import hulio13.telegramBoot.tgUserProperties.database.jsonDb.JsonTgUserPropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, TelegramApiException {
        ConfigurationService.configureWithAnnotations("hulio13", "config.json");

        ServiceRegistry.addService(TgUserPropertiesRepository.class,
                JsonTgUserPropertiesRepository.getInstance());

        UserIOService.setLocalizationHandlerId("Localization");

        Bot bot = ConfiguratorRegistry
                .get(TelegramConfigurator.class)
                .getConfiguredBot() ;

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
    }
}

