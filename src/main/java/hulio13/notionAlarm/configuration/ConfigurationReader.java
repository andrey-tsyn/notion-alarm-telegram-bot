package hulio13.notionAlarm.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationReader {
    static public Map<String, Object> readConfigurationFile(String path){
        // TODO: Some logging again
        try{
            File file = new File(path);
            return new ObjectMapper().readValue(file, HashMap.class);
        } catch (IOException e){
            System.out.println("Config file is not found or incorrect");
            System.exit(-1);
        }
        catch (Exception e){
            throw e;
        }
        return null;
    }
}
