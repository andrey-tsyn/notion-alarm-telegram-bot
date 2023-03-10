package hulio13.notionAlarm.database.jsonDb.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hulio13.notionAlarm.core.entities.User;

public final class UserJsonSerialization {
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

    public User deserialize(String json) throws JsonProcessingException {
        User user = objectMapper.readValue(json, User.class);
        for (var n :
                user.getPlannedTasks()) {
            n.setUserIfNull(user);
        }
        return user;

    }
}