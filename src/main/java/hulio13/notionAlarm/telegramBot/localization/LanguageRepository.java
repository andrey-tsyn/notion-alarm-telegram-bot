package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.exceptions.NotInitializedException;
import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import hulio13.notionAlarm.telegramBot.localization.exceptions.LanguageTagNotFoundException;
import hulio13.notionAlarm.telegramBot.localization.exceptions.PhraseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public final class LanguageRepository {
    private static final Logger logger = LoggerFactory.getLogger(LanguageRepository.class);
    private static final List<Language> langs = new LinkedList<>();
    private static Language defaultLang;
    private static boolean isInitialized = false;


    public static void Init(List<Language> languages, String defaultLanguageTag){
        if(isInitialized){

            return;
        }

        langs.addAll(languages);

        var defaultLang = langs.stream().filter(l -> l.langTag().equals(defaultLanguageTag)).findFirst();

        if (defaultLang.isPresent()){
            LanguageRepository.defaultLang = defaultLang.get();
        }
        else{
            throw new LanguageTagNotFoundException("Default language tag not found");
        }

        logger.info("Language repository is initialized.");
        isInitialized = true;
    }

    public static String getPhrase(String langTag, String phraseId){
        throwExceptionIfNotInitialized();

        Language lang = getLangWithLangTag(langTag);

        throwExceptionIfLanguageIsNull(lang, langTag);

        String phrase = lang.phrases().get(phraseId);
        if (phrase == null){
            phrase = defaultLang.phrases().get(phraseId);
            if (phrase != null){

                return phrase;
            }
            throw new PhraseNotFoundException("Phrase not found in default lang and lang " +
                    "with '" + langTag +"' language tag.");
        }
        return phrase;
    }

    public static String getButton(String langTag, String buttonId){
        throwExceptionIfNotInitialized();

        Language lang = getLangWithLangTag(langTag);

        throwExceptionIfLanguageIsNull(lang, langTag);

        String button = lang.buttons().get(buttonId);
        if (button == null){
            button = defaultLang.buttons().get(buttonId);
            if (button != null){

                return button;
            }
            throw new PhraseNotFoundException("Button not found in default lang and lang " +
                    "with '" + langTag +"' language tag.");
        }
        return button;
    }

    private static void throwExceptionIfNotInitialized(){
        if (!isInitialized){
            throw new NotInitializedException("Language repository is not initialized, call Init() before.");
        }
    }

    private static void throwExceptionIfLanguageIsNull(Language lang, String langTag){
        if (lang == null){
            logger.warn("'" + langTag + "' language tag not found.");
            throw new LanguageTagNotFoundException("Language tag not found");
        }
    }

    private static Language getLangWithLangTag(String langTag){
        return langs.stream().filter(l -> l.langTag().equals(langTag)).findFirst().get();
    }
}