package hulio13.notionAlarm.database.jsonDb.serialization.abstractions;

public interface Serializer<T> {
    String serialize(T object);
}
