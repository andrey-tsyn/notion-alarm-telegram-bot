package hulio13.notionAlarm.configuration.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hulio13.notionAlarm.configuration.ConfigurationMap;
import hulio13.notionAlarm.utils.ResourceFilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public final class JsonConfigurationFileReader {
    private static final String CONFIGURATION_FILE_NAME = "config.json";

    private static final Logger logger = LoggerFactory.getLogger(JsonConfigurationFileReader.class);

    static public ConfigurationMap readConfigurationFile(String configName) throws IOException {
        try{
            File[] files = ResourceFilesProvider.getResourceFolderFiles("");

            for (File file : files) {
                if (file.getName().equals(configName))
                    return new ObjectMapper()
                            .registerModule(new SimpleModule()
                                    .addDeserializer(
                                            ConfigurationMap.class,
                                            new JsonConfigurationDeserialization()
                                    ))
                            .readValue(file, ConfigurationMap.class);
            }
            logger.error("Configuration file '" + CONFIGURATION_FILE_NAME +
                    "' not found in root of resource folder");
            throw new FileNotFoundException("Configuration file not found");
        } catch (IOException e){
            logger.error("Config file is not found or incorrect");
            throw new IOException(e);
        }
    }
}
