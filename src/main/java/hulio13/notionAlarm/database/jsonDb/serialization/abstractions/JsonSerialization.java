package hulio13.notionAlarm.database.jsonDb.serialization.abstractions;

import com.fasterxml.jackson.core.JsonProcessingException;
import hulio13.notionAlarm.database.jsonDb.serialization.exceptions.JsonReadException;

public interface JsonSerialization<T> extends Deserializer<T>, Serializer<T> {}


