package hulio13.notionAlarm.database.jsonDb.serialization.exceptions;

public class JsonReadException extends Exception {
    public JsonReadException() {
    }

    public JsonReadException(String message) {
        super(message);
    }

    public JsonReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonReadException(Throwable cause) {
        super(cause);
    }
}
