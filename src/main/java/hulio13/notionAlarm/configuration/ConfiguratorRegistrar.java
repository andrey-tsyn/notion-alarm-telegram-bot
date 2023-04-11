package hulio13.notionAlarm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class ConfiguratorRegistrar extends ConfiguratorRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ConfiguratorRegistrar.class);

    public static <T> void register(Class<T> type, T serviceImpl) {
        logger.trace("Configurator '" + type.getName() + "' added.");
        ConfiguratorRegistry.add(type, serviceImpl);
    }
}