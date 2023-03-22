package hulio13.notionAlarm.telegramBot.localization.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LanguageJsonDeserializer extends StdDeserializer<Language> {
    private static final Logger logger = LoggerFactory.getLogger(LanguageJsonDeserializer.class);

    public LanguageJsonDeserializer(){
        this(LanguageJsonDeserializer.class);
    }

    protected LanguageJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Language deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String langTag = node.get("langTag").asText();
        DualHashBidiMap<String, String> buttonBidiMap = new DualHashBidiMap();
        DualHashBidiMap<String, String> phrasesBidiMap = new DualHashBidiMap<>();

        JsonNode buttonNode = node.get("buttons");
        process(buttonBidiMap, "", buttonNode, true);
        JsonNode phraseNode = node.get("phrases");
        process(phrasesBidiMap, "", phraseNode, true);

        Language lang = new Language(langTag, phrasesBidiMap, buttonBidiMap);
        logger.debug("Language with tag " + "\"" + langTag + "\" deserialized.");
        return lang;
    }

    public void process(BidiMap<String, String> map, String lastStr,
                        JsonNode root, boolean skipFirstWord){
        if (root.isContainerNode()) {
            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                var next = fields.next();

                logger.trace("Path: " + lastStr);

                String nextPath = skipFirstWord ? next.getKey() : lastStr + "." + next.getKey();
                process(map, nextPath, next.getValue(), false);
            }
        }
        else{
            logger.trace("Added phrase: {" + lastStr + ": " + root.asText() + "}");
            map.put(lastStr, root.asText());
        }
    }
}
