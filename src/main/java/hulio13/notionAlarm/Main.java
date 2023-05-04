package hulio13.notionAlarm;

import hulio13.notionAlarm.configuration.ConfigurationService;
import hulio13.notionAlarm.configuration.ConfiguratorRegistry;
import hulio13.notionAlarm.configuration.configurators.CoreConfigurator;
import hulio13.notionAlarm.configuration.configurators.TelegramConfigurator;
import hulio13.notionAlarm.configuration.json.JsonConfigurationFileReader;
import hulio13.notionAlarm.telegramBot.Bot;
import hulio13.notionAlarm.telegramBot.ServiceRegistry;
import hulio13.notionAlarm.telegramBot.UserIOService;
import hulio13.notionAlarm.telegramBot.localization.LanguageRepository;
import hulio13.notionAlarm.telegramBot.localization.LocalizationService;
import hulio13.notionAlarm.telegramBot.localization.json.JsonLanguagesProvider;
import hulio13.notionAlarm.telegramBot.tgUserProperties.database.TgUserPropertiesRepository;
import hulio13.notionAlarm.telegramBot.tgUserProperties.database.jsonDb.JsonTgUserPropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, TelegramApiException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LocalizationService.init(new LanguageRepository(
                new JsonLanguagesProvider("localization/").getAll(),
                "ru-RU"));

        JsonConfigurationFileReader.readConfigurationFile("config.json");

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

