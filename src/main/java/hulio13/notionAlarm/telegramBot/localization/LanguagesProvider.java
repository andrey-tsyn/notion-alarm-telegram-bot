package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.telegramBot.localization.entities.Language;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class LanguagesProvider {
    private static final String PATH = "./localization";
    public static List<Language> getAll(){
        Path path = Paths.get(PATH);
        return null;
    }
}
