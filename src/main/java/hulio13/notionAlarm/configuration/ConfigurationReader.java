package hulio13.notionAlarm.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class ConfigurationReader {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationReader.class);

    static public Map<String, Object> readConfigurationFile(String configName) throws IOException {
        try{
            InputStream is = ConfigurationReader.class.getResourceAsStream("/" + configName);
            return new ObjectMapper().readValue(is, HashMap.class);
        } catch (IOException e){
            logger.error("Config file is not found or incorrect");
            throw new IOException(e);
        }
    }
}
