package hulio13.notionAlarm.telegramBot.localization.exceptions;

public final class LanguageTagNotFoundException extends RuntimeException{
    public LanguageTagNotFoundException() {
    }

    public LanguageTagNotFoundException(String message) {
        super(message);
    }
}
