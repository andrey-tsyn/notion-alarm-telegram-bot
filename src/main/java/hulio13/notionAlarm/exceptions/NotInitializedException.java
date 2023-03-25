package hulio13.notionAlarm.exceptions;

public final class NotInitializedException extends RuntimeException{
    public NotInitializedException() {
    }

    public NotInitializedException(String message) {
        super(message);
    }
}
