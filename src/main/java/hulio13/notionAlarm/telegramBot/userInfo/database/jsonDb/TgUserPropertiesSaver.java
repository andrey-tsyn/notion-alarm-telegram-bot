package hulio13.notionAlarm.telegramBot.userInfo.database.jsonDb;

import hulio13.notionAlarm.core.abstractions.Repository;
import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import hulio13.notionAlarm.database.jsonDb.serialization.abstractions.JsonSerialization;
import hulio13.notionAlarm.telegramBot.userInfo.TgUserProperties;

import java.io.IOException;

public final class TgUserPropertiesSaver extends JsonSaver<TgUserProperties> {
    public TgUserPropertiesSaver(String pathToFolder, Repository<TgUserProperties> jsonRepository, JsonSerialization serializationProvider) {
        super(pathToFolder, jsonRepository, serializationProvider);
    }

    @Override
    public void saveAll() {
        repository.forEach(tgUserProperties -> {
                try {
                    save(tgUserProperties, tgUserProperties.telegramId);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
