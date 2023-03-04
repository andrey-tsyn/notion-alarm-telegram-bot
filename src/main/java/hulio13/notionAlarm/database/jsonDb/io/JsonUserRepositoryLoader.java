package hulio13.notionAlarm.database.jsonDb.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import hulio13.notionAlarm.configuration.ConfigurationReader;
import hulio13.notionAlarm.database.exceptions.EntityNotFoundException;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.providers.UserIdsProvider;
import hulio13.notionAlarm.database.jsonDb.providers.UserJsonProvider;
import hulio13.notionAlarm.database.jsonDb.providers.UserJsonSerializationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class JsonUserRepositoryLoader {
    private static Logger logger = LoggerFactory.getLogger(JsonUserRepositoryLoader.class);
    static public List<User> loadUsers(String pathToFolder){
        List<String> telegramIds = null;
        UserJsonSerializationProvider userJsonSerializationProvider = new UserJsonSerializationProvider();

        try{
            telegramIds = UserIdsProvider.getIdsFromDatabaseFolder(pathToFolder);
        }
        catch (IOException e){
            logger.error("Access to files failed, maybe no permissions granted.");
            throw new RuntimeException(e);
        }

        List<User> users = new LinkedList<>();
        UserJsonProvider jsonProvider = new UserJsonProvider(pathToFolder);
        for (var tgId :
                telegramIds) {
            try{
                users.add(userJsonSerializationProvider.deserialize(jsonProvider.readJsonWithName(tgId)));
            }
            catch (JsonProcessingException e){
                logger.warn(String.format("Incorrect format of json file or don't have necessary values. Error message: %s",
                        e.getMessage()));
            }
        }

        return users;
    }
}
