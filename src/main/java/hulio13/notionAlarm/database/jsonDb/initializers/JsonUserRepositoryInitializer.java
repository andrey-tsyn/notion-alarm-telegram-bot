package hulio13.notionAlarm.database.jsonDb.initializers;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.DumpingScheduler;
import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import hulio13.notionAlarm.database.jsonDb.io.UserJsonSaver;
import hulio13.notionAlarm.database.jsonDb.serialization.UserJsonSerialization;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.notionAlarm.database.jsonDb.io.JsonRepositoryLoader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public final class JsonUserRepositoryInitializer {
    static public void initialize(String databaseFolder, int delayToSaveInFolder, TimeUnit timeUnit){

        List<User> users = new JsonRepositoryLoader<User>().load(databaseFolder, new UserJsonSerialization());

        JsonUserRepository.getInstance(users, databaseFolder);
        JsonSaver saver = new UserJsonSaver(databaseFolder, JsonUserRepository.getInstance(), new UserJsonSerialization());

        DumpingScheduler.start(saver, delayToSaveInFolder, timeUnit);
    }
}
