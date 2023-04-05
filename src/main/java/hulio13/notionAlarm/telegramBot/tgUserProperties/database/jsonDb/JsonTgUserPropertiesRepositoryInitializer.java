package hulio13.notionAlarm.telegramBot.tgUserProperties.database.jsonDb;

import hulio13.notionAlarm.database.jsonDb.DumpingScheduler;
import hulio13.notionAlarm.database.jsonDb.io.JsonRepositoryLoader;
import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public final class JsonTgUserPropertiesRepositoryInitializer {
    private static Logger logger = LoggerFactory.getLogger(JsonTgUserPropertiesRepositoryInitializer.class);

    public static void initialize(String dbFolder, int delayToSaveInDb, TimeUnit unit){
        List<TgUserProperties> tgUserProperties = new JsonRepositoryLoader<TgUserProperties>().load(dbFolder, new TgUserPropertiesSerialization());

        var repository =
                JsonTgUserPropertiesRepository.getInstance(tgUserProperties);

        JsonSaver<TgUserProperties> saver = new TgUserPropertiesSaver(
                dbFolder,
                repository,
                new TgUserPropertiesSerialization()
        );

        DumpingScheduler.start(saver, delayToSaveInDb, unit);
    }
}
