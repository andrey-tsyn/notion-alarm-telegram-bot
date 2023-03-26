package hulio13.notionAlarm.telegramBot.localization.exceptions;

public final class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
