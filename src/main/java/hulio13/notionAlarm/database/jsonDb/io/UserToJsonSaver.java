package hulio13.notionAlarm.database.jsonDb.io;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.providers.UserJsonProvider;
import hulio13.notionAlarm.database.jsonDb.providers.UserJsonSerializationProvider;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;

public class UserToJsonSaver {
    private final UserJsonProvider provider;
    private final JsonUserRepository repository;
    private final UserJsonSerializationProvider serializationProvider;

    public UserToJsonSaver(String pathToFolder, JsonUserRepository jsonUserRepository,
                           UserJsonSerializationProvider serializationProvider) {
        this.provider = new UserJsonProvider(pathToFolder);
        this.repository = jsonUserRepository;
        this.serializationProvider = serializationProvider;
    }

    public void saveAll(){
        repository.forEach(user -> {
            provider.writeJsonWithName(user.telegramId, serializationProvider.serialize(user));
        });
    }

    public void save(User user){
        provider.writeJsonWithName(user.telegramId, serializationProvider.serialize(user));
    }
}
