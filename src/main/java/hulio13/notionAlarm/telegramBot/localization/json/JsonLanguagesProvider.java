package hulio13.notionAlarm.telegramBot.localization.json;

import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;
import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import hulio13.notionAlarm.utils.ResourceFilesProvider;
import hulio13.notionAlarm.utils.StringFromFileExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public final class JsonLanguagesProvider {
    private static Logger logger = LoggerFactory.getLogger(JsonLanguagesProvider.class);

    private final String pathInResourcesFolder;

    public JsonLanguagesProvider(String pathInResourcesFolder) {
        this.pathInResourcesFolder = pathInResourcesFolder;
    }

    public List<Language> getAll(){
        File[] files = ResourceFilesProvider.getResourceFolderFiles(pathInResourcesFolder);

        LinkedList<Language> languages = new LinkedList<>();
        LanguageJsonSerialization serialization = new LanguageJsonSerialization();

        for (File file : files) {
            String json = StringFromFileExtractor.extractString(file);
            try {
                languages.add(serialization.deserialize(json));
            } catch (JsonReadException e) {
                logger.warn("File with name " + file.getName() + " is not json. It's not added.");
            }
        }

        return languages;
    }
}
