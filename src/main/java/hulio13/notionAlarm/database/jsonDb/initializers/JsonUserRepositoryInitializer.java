package hulio13.notionAlarm.database.jsonDb.initializers;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.UsersDumpingScheduler;
import hulio13.notionAlarm.database.jsonDb.io.UserToJsonSaver;
import hulio13.notionAlarm.database.jsonDb.providers.UserJsonSerializationProvider;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.notionAlarm.database.jsonDb.io.JsonUserRepositoryLoader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class JsonUserRepositoryInitializer {
    static public JsonUserRepository initialize(String databaseFolder, int delayToSaveInFolder, TimeUnit timeUnit){
        List<User> users = JsonUserRepositoryLoader.loadUsers(databaseFolder);
        JsonUserRepository repository = new JsonUserRepository(users, databaseFolder);
        UserToJsonSaver saver = new UserToJsonSaver(databaseFolder, repository, new UserJsonSerializationProvider());

        UsersDumpingScheduler.start(saver, delayToSaveInFolder, timeUnit);

        return repository;
    }
}
