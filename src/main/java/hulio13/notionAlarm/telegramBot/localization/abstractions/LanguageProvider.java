package hulio13.notionAlarm.telegramBot.localization.abstractions;

import hulio13.notionAlarm.telegramBot.localization.entities.Language;

import java.util.List;

public interface LanguageProvider {
    List<Language> getAll();
}
