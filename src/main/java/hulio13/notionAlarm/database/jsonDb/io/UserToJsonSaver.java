package hulio13.notionAlarm.database.jsonDb.io;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.providers.JsonProvider;
import hulio13.notionAlarm.database.jsonDb.serialization.UserJsonSerialization;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserToJsonSaver {
    private final JsonProvider provider;
    private final JsonUserRepository repository;
    private final UserJsonSerialization serializationProvider;
    static private final Logger logger = LoggerFactory.getLogger(UserToJsonSaver.class);

    public UserToJsonSaver(String pathToFolder, JsonUserRepository jsonUserRepository,
                           UserJsonSerialization serializationProvider) {
        this.provider = new JsonProvider(pathToFolder);
        this.repository = jsonUserRepository;
        this.serializationProvider = serializationProvider;
    }

    public void saveAll() {
        repository.forEach(user -> {
            try {
                save(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void save(User user) throws IOException {
        try {
            provider.writeJsonWithName(user.telegramId, serializationProvider.serialize(user));
        } catch (IOException e) {
            logger.warn(String.format("Unable to save to file, maybe don't have permissions? TelegramId: %s",
                    user.telegramId));
            throw new IOException(e);
        }
    }
}
