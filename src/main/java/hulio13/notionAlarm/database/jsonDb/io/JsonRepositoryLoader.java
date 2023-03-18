package hulio13.notionAlarm.database.jsonDb.io;

import hulio13.notionAlarm.database.jsonDb.providers.JsonFilesProvider;
import hulio13.notionAlarm.database.jsonDb.providers.JsonProvider;
import hulio13.notionAlarm.database.jsonDb.serialization.abstractions.JsonSerialization;
import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class JsonRepositoryLoader<T>{
    private static Logger logger = LoggerFactory.getLogger(JsonRepositoryLoader.class);
    public List<T> load(String pathToFolder, JsonSerialization<T> jsonSerialization){
        List<String> fileNames;

        try{
            fileNames = JsonFilesProvider.getFileListFromFolder(pathToFolder);
        }
        catch (IOException e){
            logger.error("Access to files failed, maybe no permissions granted or directory does not exist.");
            throw new RuntimeException(e);
        }

        List<T> objects = new LinkedList<>();
        JsonProvider jsonProvider = new JsonProvider(pathToFolder);
        for (var fileName :
                fileNames) {
            try{
                objects.add((T) jsonSerialization.deserialize(jsonProvider.readJsonWithName(fileName)));
            }
            catch (JsonReadException e){
                logger.warn(String.format("Incorrect format of json file or don't have necessary values. Error message: " + e.getMessage()));
            }
            catch (ClassCastException e){
                logger.error("");
            }
        }

        return objects;
    }


}
