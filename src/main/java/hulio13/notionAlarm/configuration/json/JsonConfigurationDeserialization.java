package hulio13.notionAlarm.configuration.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hulio13.notionAlarm.configuration.ConfigurationMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class JsonConfigurationDeserialization extends StdDeserializer<ConfigurationMap> {
    private static final Logger logger =
            LoggerFactory.getLogger(JsonConfigurationDeserialization.class);

    public JsonConfigurationDeserialization() {
        this(JsonConfigurationDeserialization.class);
    }

    private JsonConfigurationDeserialization(Class<?> vc) {
        super(vc);
    }

    @Override
    public ConfigurationMap deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        HashMap<String, String> map = new HashMap<>();

        if (!isNodeNullOrEmpty(node, "")) {
            process(map, "", node);
        }

        ConfigurationMap configurationMap = new ConfigurationMap(map);
        logger.info("Configuration map deserialized.");
        return configurationMap;
    }

    public void process(Map<String, String> map, String lastStr,
                        JsonNode root) {
        if (root.isContainerNode()) {
            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                var next = fields.next();

                logger.trace("Path: " + lastStr);

                String nextPath = lastStr.equals("") ?
                        next.getKey() : lastStr + "." + next.getKey();
                process(map, nextPath, next.getValue());
            }
        } else {
            logger.debug("Add value: {" + lastStr + ": " + root.asText() + "}");
            map.put(lastStr, root.asText());
        }
    }

    public boolean isNodeNullOrEmpty
            (JsonNode jsonNode,
             String keyNameOfNode) {
        if (jsonNode == null) {
            logger.warn("\"" + keyNameOfNode +
                    "\" key not found in json");
            return true;
        }
        if (jsonNode.isEmpty()){
            logger.warn("\"" + keyNameOfNode +
                    "\" key has no value in json");
            return true;
        }
        return false;
    }
}
