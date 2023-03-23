package hulio13.notionAlarm.telegramBot.localization.exceptions;

public final class EmptyRequiredKeyException extends RuntimeException{
    public EmptyRequiredKeyException() {
    }

    public EmptyRequiredKeyException(String message) {
        super(message);
    }

    public EmptyRequiredKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequiredKeyException(Throwable cause) {
        super(cause);
    }
}
