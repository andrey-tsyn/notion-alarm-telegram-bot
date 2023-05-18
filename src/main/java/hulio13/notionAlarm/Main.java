package hulio13.notionAlarm;

import hulio13.configuration.ConfigurationService;
import hulio13.configuration.ConfiguratorRegistry;
import hulio13.notionAlarm.configuration.configurators.TelegramConfigurator;
import hulio13.telegramBoot.Bot;
import hulio13.telegramBoot.ServiceRegistry;
import hulio13.telegramBoot.UserIOService;
import hulio13.telegramBoot.tgUserProperties.database.TgUserPropertiesRepository;
import hulio13.telegramBoot.tgUserProperties.database.jsonDb.JsonTgUserPropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws TelegramApiException {
        ConfigurationService.configureWithAnnotations("hulio13", "config.json");

        ServiceRegistry.addService(TgUserPropertiesRepository.class,
                JsonTgUserPropertiesRepository.getInstance());

        UserIOService.setLocalizationHandlerId("Localization");

        Bot bot = ConfiguratorRegistry
                .get(TelegramConfigurator.class)
                .getConfiguredBot();

        bot.start();
    }
}

