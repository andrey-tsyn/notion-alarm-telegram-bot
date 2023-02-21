package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.telegramBot.localization.entities.Language;

import java.util.LinkedList;
import java.util.List;

public final class LanguageRepository {
    private static Language defaultLang;
    private static final String DEFAULT_LANGUAGE = "ru-RU";
    private static final List<Language> langs = new LinkedList<>();

    public static void Init(){
        // TODO: Some logging
        if (langs.isEmpty()) langs.addAll(LanguagesProvider.getAll());

        if (langs.contains(DEFAULT_LANGUAGE)){
            if (defaultLang != null){
                // TODO: Some logging
                throw new RuntimeException("Default language tag already assigned");
            }
            defaultLang = langs.stream().filter(l -> l.langTag().equals(DEFAULT_LANGUAGE)).findFirst().get();
        }
        else{
            throw new RuntimeException("Default language tag not found");
        }
    }
    public static String getPhrase(String langTag, String phraseId){
        if (langs.isEmpty()){
            throw new RuntimeException("Static class is not initialized, call Init() before");
        }


        Language lang = langs.stream().filter(l -> l.langTag().equals(langTag)).findFirst().get();

        if (lang == null){
            throw new RuntimeException("Language tag not found");
        }

        String phrase = lang.phrases().get(phraseId);
        if (phrase == null){
            // TODO: Some logging
            return defaultLang.phrases().get(phraseId);
        }
        return phrase;
    }
}
