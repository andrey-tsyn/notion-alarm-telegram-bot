package hulio13.notionAlarm.telegramBot.inputHandlers;

import hulio13.notionAlarm.telegramBot.userInfo.TgUserProperties;

public interface InputHandler {
    String getId();
    void processInput(String input, TgUserProperties properties);
    MessageContainer getMessageForUser();
}