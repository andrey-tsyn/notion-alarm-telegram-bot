package hulio13.notionAlarm.database.jsonDb.initializers;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.notionAlarm.database.jsonDb.io.JsonUserRepositoryLoader;

import java.util.List;

public final class JsonUserRepositoryInitializer {
    static public JsonUserRepository initialize(String databaseFolder){
        List<User> users = JsonUserRepositoryLoader.loadUsers(databaseFolder);
        JsonUserRepository repository = new JsonUserRepository(users, databaseFolder);

        return repository;
    }
}
