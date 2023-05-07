package hulio13.notionAlarm.telegramBot.inputHandlers;

import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;

public interface InputHandler {
    String getId();

    default boolean HasNonLocalizableText(){return false;}

    void processInput(String input, TgUserProperties properties);

    MessageContainer getMessageForUser();
}