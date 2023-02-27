package hulio13.notionAlarm.database.jsonDb.initializers;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.notionAlarm.database.jsonDb.loaders.JsonUserRepositoryLoader;

import java.util.List;

public final class JsonUserRepositoryInitializer {
    static public JsonUserRepository initialize(String databaseFolder, int intervalInMinutesToWriteInFolder){
        List<User> users = JsonUserRepositoryLoader.loadUsers(databaseFolder);
        JsonUserRepository repository = new JsonUserRepository(users);

        return repository;
    }
}