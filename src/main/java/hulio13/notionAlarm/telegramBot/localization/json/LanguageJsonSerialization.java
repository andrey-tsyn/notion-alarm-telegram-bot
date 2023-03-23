package hulio13.notionAlarm.telegramBot.localization.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hulio13.notionAlarm.database.jsonDb.serialization.abstractions.JsonSerialization;
import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;
import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageJsonSerialization implements JsonSerialization<Language> {
    private static final Logger logger = LoggerFactory.getLogger(LanguageJsonSerialization.class);
    private final ObjectMapper objectMapper;
    public LanguageJsonSerialization() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Language.class, new LanguageJsonDeserializer());
        objectMapper.registerModule(module);
    }

    public String serialize(Language lang) {
        return null;
    }

    public Language deserialize(String json) throws JsonReadException {
        Language language = null;
        try {
            language = objectMapper.readValue(json, Language.class);
        } catch (JsonProcessingException e) {
            throw new JsonReadException(e);
        }
        return language;
    }
}
