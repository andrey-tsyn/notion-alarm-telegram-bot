package hulio13.notionAlarm.database.jsonDb;

import hulio13.notionAlarm.database.exceptions.EntityNotFoundException;
import hulio13.notionAlarm.core.entities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class JsonUserRepositoryLoader {
    static public List<User> loadUsers(String pathToFolder){
        List<String> telegramIds = null;
        UserJsonSerializationProvider userJsonSerializationProvider = new UserJsonSerializationProvider();

        try{
            telegramIds = UserIdsProvider.getIdsFromDatabaseFolder(pathToFolder);
        }
        catch (Exception e){
            // TODO: some logging and exit
        }

        List<User> users = new LinkedList<>();
        UserJsonProvider jsonProvider = new UserJsonProvider(pathToFolder);
        for (var tgId :
                telegramIds) {
            try{
                users.add(userJsonSerializationProvider.deserialize(jsonProvider.readJsonWithName(tgId)));
            }
            catch (EntityNotFoundException e){
                // TODO: some logging
            }
            catch (IOException e){
                // TODO: some logging
            }
        }

        return users;
    }
}
