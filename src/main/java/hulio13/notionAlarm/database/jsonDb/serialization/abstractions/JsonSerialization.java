package hulio13.notionAlarm.database.jsonDb.serialization.abstractions;

import com.fasterxml.jackson.core.JsonProcessingException;
import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;

public interface JsonSerialization<T> {
    String serialize(T object);
    T deserialize(String json) throws JsonReadException;
}
