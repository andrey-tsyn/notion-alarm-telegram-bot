package hulio13.notionAlarm.telegramBot.inputHandlers;

import hulio13.notionAlarm.telegramBot.userInfo.TgUserProperties;

public interface InputHandler {
    Response processInput(String input, TgUserProperties properties);
}