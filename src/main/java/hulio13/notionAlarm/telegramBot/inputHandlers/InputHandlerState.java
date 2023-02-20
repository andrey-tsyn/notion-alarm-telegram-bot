package hulio13.notionAlarm.telegramBot.inputHandlers;

import java.util.Map;

public record InputHandlerState(String UserId, String handlerId, Map<String, Object> payload) { }