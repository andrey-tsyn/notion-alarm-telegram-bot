package hulio13.notionAlarm.telegramBot.localization.entities;

import org.apache.commons.collections4.BidiMap;

public record Language(String langTag, BidiMap<String, String> phrases) {
}
