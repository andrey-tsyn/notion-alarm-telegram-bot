package hulio13.notionAlarm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ConfiguratorRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ConfiguratorRegistrar.class);

    private static final Map<Class<?>, Object> configurators = new HashMap<>();

    // Calls with reflection
    protected static <T> void add(Class<T> type, T serviceImpl) {
        logger.trace("Configurator '" + type.getName() + "' added.");
        configurators.put(type, serviceImpl);
    }

    public static <T> T get(Class<T> type) {
        return type.cast(configurators.get(type));
    }
}
