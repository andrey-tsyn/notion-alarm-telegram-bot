package hulio13.notionAlarm.telegramBot.tgUserProperties.database.jsonDb;

import hulio13.notionAlarm.database.jsonDb.io.JsonRepositoryLoader;
import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public final class JsonTgUserPropertiesRepositoryInitializer {
    private static Logger logger = LoggerFactory.getLogger(JsonTgUserPropertiesRepositoryInitializer.class);

    public static JsonTgUserPropertiesRepository initialize(String dbFolder, int delayToSaveInDb, TimeUnit unit){
        List<TgUserProperties> tgUserProperties = new JsonRepositoryLoader<TgUserProperties>().load(dbFolder, new TgUserPropertiesSerialization());

        JsonTgUserPropertiesRepository repository = new JsonTgUserPropertiesRepository(tgUserProperties);
        JsonSaver<TgUserProperties> saver = new TgUserPropertiesSaver(dbFolder, repository, new TgUserPropertiesSerialization());

        return null;
    }
}
