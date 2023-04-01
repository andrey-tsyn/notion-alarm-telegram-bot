package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import hulio13.notionAlarm.telegramBot.localization.exceptions.LanguageTagNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public final class LanguageRepository {
    private static final Logger logger = LoggerFactory.getLogger(LanguageRepository.class);
    private static final List<Language> langs = new LinkedList<>();
    private static Language defaultLang;


    public LanguageRepository(List<Language> languages, String defaultLanguageTag){
        langs.addAll(languages);

        var defaultLang = langs.stream()
                .filter(l -> l.langTag().equals(defaultLanguageTag))
                .findFirst();

        if (defaultLang.isPresent()){
            LanguageRepository.defaultLang = defaultLang.get();
        }
        else{
            throw new LanguageTagNotFoundException("Default language tag not found");
        }

        logger.info("Language repository is initialized.");

    }

    public Language getLanguageByLanguageTag(String langTag){
        synchronized (langs){
            return langs.stream().filter(l -> l.langTag().equals(langTag)).findFirst().get();
        }
    }

    public Language getDefaultLang() {
        return defaultLang;
    }


}