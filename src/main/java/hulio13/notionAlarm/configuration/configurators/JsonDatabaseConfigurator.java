package hulio13.notionAlarm.configuration.configurators;

import hulio13.notionAlarm.configuration.annotations.Configuration;
import hulio13.notionAlarm.configuration.annotations.Value;
import hulio13.notionAlarm.database.jsonDb.initializers.JsonUserRepositoryInitializer;
import hulio13.telegramBoot.tgUserProperties.database.jsonDb.JsonTgUserPropertiesRepositoryInitializer;

import java.util.concurrent.TimeUnit;

@Configuration(defaultPath = "json")
public class JsonDatabaseConfigurator {
    @Value(path = "databaseFolders.users")
    private String userFolder;

    @Value(path = "databaseFolders.telegramProperties")
    private String tgUserPropertiesFolder;

    @Value(path = "saveFrequencyInMinutes")
    private String saveFrequencyInMinutes;

    public void configure() {
        String userPath = System.getProperty("user.dir") + tgUserPropertiesFolder;
        String tgPropertiesPath = System.getProperty("user.dir") + tgUserPropertiesFolder;

        JsonUserRepositoryInitializer.initialize(
                userPath,
                Integer.parseInt(saveFrequencyInMinutes),
                TimeUnit.MINUTES);

        JsonTgUserPropertiesRepositoryInitializer.initialize(
                tgPropertiesPath,
                Integer.parseInt(saveFrequencyInMinutes),
                TimeUnit.MINUTES);
    }
}
