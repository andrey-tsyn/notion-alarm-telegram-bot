package hulio13.notionAlarm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ConfiguratorRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ConfiguratorRegistry.class);

    private static final Map<Class<?>, Object> configurators = new HashMap<>();

    public static void register(Class<?> type, Object serviceImpl) {
        logger.trace("Configurator '" + type.getName() + "' added.");
        configurators.put(type, serviceImpl);
    }

    public static <T> T get(Class<T> type) {
        return type.cast(configurators.get(type));
    }
}
