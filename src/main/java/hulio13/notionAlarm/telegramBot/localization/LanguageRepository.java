package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import hulio13.notionAlarm.telegramBot.localization.exceptions.LanguageTagNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class LanguageRepository {
    private static final Logger logger = LoggerFactory.getLogger(LanguageRepository.class);
    private final List<Language> langs = new LinkedList<>();
    private Language defaultLang;


    public LanguageRepository(List<Language> languages, String defaultLanguageTag){
        langs.addAll(languages);

        var defaultLang = langs.stream()
                .filter(l -> l.langTag().equals(defaultLanguageTag))
                .findFirst();

        if (defaultLang.isPresent()){
            this.defaultLang = defaultLang.get();
        }
        else{
            throw new LanguageTagNotFoundException("Default language tag not found");
        }

        logger.info("Language repository is initialized.");

    }

    public Language getLanguageByLanguageTag(String langTag){
        synchronized (langs){
            Optional<Language> lang = langs.stream()
                    .filter(l -> l.langTag().equals(langTag))
                    .findFirst();
            return lang.isPresent() ? lang.get() : null;
        }
    }

    public Language getDefaultLang() {
        return defaultLang;
    }


}