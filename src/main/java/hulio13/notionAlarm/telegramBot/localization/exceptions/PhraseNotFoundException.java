package hulio13.notionAlarm.telegramBot.localization.exceptions;

public final class PhraseNotFoundException extends RuntimeException{
    public PhraseNotFoundException() {
    }

    public PhraseNotFoundException(String message) {
        super(message);
    }
}
