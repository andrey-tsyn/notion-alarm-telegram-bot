package hulio13.notionAlarm.database.jsonDb.serialization.abstractions;

import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;

public interface Deserializer<T> {
    T deserialize(String json) throws JsonReadException;
}
