package hulio13.notionAlarm.database.jsonDb.initializers;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.UsersDumpingScheduler;
import hulio13.notionAlarm.database.jsonDb.io.UserToJsonSaver;
import hulio13.notionAlarm.database.jsonDb.serialization.UserJsonSerialization;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.notionAlarm.database.jsonDb.io.JsonRepositoryLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public final class JsonUserRepositoryInitializer {
    static public JsonUserRepository initialize(String databaseFolder, int delayToSaveInFolder, TimeUnit timeUnit){

        List<User> users = JsonRepositoryLoader.load(databaseFolder, new UserJsonSerialization())
                .stream()
                .map(o -> (User) o)
                .collect(Collectors.toCollection(LinkedList::new));

        JsonUserRepository repository = new JsonUserRepository(users, databaseFolder);
        UserToJsonSaver saver = new UserToJsonSaver(databaseFolder, repository, new UserJsonSerialization());

        UsersDumpingScheduler.start(saver, delayToSaveInFolder, timeUnit);

        return repository;
    }
}
