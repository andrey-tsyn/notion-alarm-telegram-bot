package hulio13.notionAlarm.database.exceptions;

public class EntityNotFoundException extends Exception {
    public String id;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String id) {
        this.id = id;
    }

    public EntityNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }
}
