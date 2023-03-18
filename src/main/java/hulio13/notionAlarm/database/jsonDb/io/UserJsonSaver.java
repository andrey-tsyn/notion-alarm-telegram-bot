package hulio13.notionAlarm.database.jsonDb.io;

import hulio13.notionAlarm.core.abstractions.Repository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.serialization.abstractions.JsonSerialization;

import java.io.IOException;

public final class UserJsonSaver extends JsonSaver<User>{
    public UserJsonSaver(String pathToFolder, Repository<User> jsonRepository, JsonSerialization serializationProvider) {
        super(pathToFolder, jsonRepository, serializationProvider);
    }

    public void saveAll() {
        repository.forEach(user -> {
            try {
                save(user, user.id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
