package hulio13.notionAlarm.configuration;

import hulio13.notionAlarm.configuration.json.JsonConfigurationFileReader;
import hulio13.notionAlarm.configuration.reflections.AnnotationsConfiguratorService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class ConfigurationService {
    private static final Logger logger =
            LoggerFactory.getLogger(ConfigurationService.class);

    public static void configureWithAnnotations(String configPackage, String configFileName) {
        ConfigurationMap map;
        try {
            map = JsonConfigurationFileReader.readConfigurationFile(configFileName);
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }

        AnnotationsConfiguratorService.configure(configPackage, map);
    }
}
