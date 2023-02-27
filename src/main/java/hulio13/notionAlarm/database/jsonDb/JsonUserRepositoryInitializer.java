package hulio13.notionAlarm.database.jsonDb;

import hulio13.notionAlarm.core.entities.User;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class JsonUserRepositoryInitializer {
    static public JsonUserRepository initialize(String databaseFolder, int intervalInMinutesToWriteInFolder){
        List<User> users = JsonUserRepositoryLoader.loadUsers(databaseFolder);
        JsonUserRepository repository = new JsonUserRepository(users);

        return repository;
    }
}
