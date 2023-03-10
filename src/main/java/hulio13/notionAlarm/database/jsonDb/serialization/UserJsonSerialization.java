package hulio13.notionAlarm.database.jsonDb.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.serialization.abstractions.JsonSerialization;
import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UserJsonSerialization implements JsonSerialization<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserJsonSerialization.class);
    private final ObjectMapper objectMapper;
    public UserJsonSerialization() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public String serialize(User user) {
        try {
            String str = objectMapper.writeValueAsString(user);
            return str;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public User deserialize(String json) throws JsonReadException {
        User user = null;
        try {
            user = objectMapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            throw new JsonReadException(e);
        }
        for (var n :
                user.getPlannedTasks()) {
            n.setUserIfNull(user);
        }
        return user;

    }
}
