package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.core.Result;
import hulio13.notionAlarm.exceptions.NotInitializedException;
import hulio13.notionAlarm.telegramBot.localization.entities.Language;
import hulio13.notionAlarm.telegramBot.localization.exceptions.LanguageTagNotFoundException;
import hulio13.notionAlarm.telegramBot.localization.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Refactoring
public final class LocalizationService {
    private static final Logger logger = LoggerFactory.getLogger(LocalizationService.class);

    private static LanguageRepository repository;
    private static boolean isInitialized = false;

    public static void init(LanguageRepository repository){
        LocalizationService.repository = repository;
        isInitialized = true;
    }

    public static Result<String> getPhraseById(String langTag, String phraseId){
        throwExceptionIfNotInitialized();

        Language lang = repository.getLanguageByLanguageTag(langTag);

        throwExceptionIfLanguageIsNull(lang, langTag);

        String phrase = lang.phrases().get(phraseId);
        if (phrase == null){
            phrase = repository.getDefaultLang().phrases().get(phraseId);
            if (phrase != null){
                return new Result<>(phrase);
            }
            return new Result<>("not_found", "Phrase not found in default lang and lang " +
                    "with '" + langTag +"' language tag.");
        }
        return new Result<>(phrase);
    }

    public static Result<String> getButtonById(String langTag, String buttonId){
        throwExceptionIfNotInitialized();

        Language lang = repository.getLanguageByLanguageTag(langTag);

        throwExceptionIfLanguageIsNull(lang, langTag);

        String button = lang.buttons().get(buttonId);
        if (button == null){
            button = repository.getDefaultLang().buttons().get(buttonId);
            if (button != null){
                return new Result<>(button);
            }
            return new Result<>("not_found", "Button not found in default lang and lang " +
                    "with '" + langTag +"' language tag.");
        }
        return new Result<>(button);
    }

    public static Result<String> getPhraseByValue(String langTag, String phraseValue){
        throwExceptionIfNotInitialized();

        Language lang = repository.getLanguageByLanguageTag(langTag);

        throwExceptionIfLanguageIsNull(lang, langTag);

        String phrase = lang.phrases().getKey(phraseValue);
        if (phrase == null){
            phrase = repository.getDefaultLang().phrases().getKey(phraseValue);
            if (phrase != null){
                return new Result<>(phrase);
            }
            return new Result<>("not_found", "Phrase not found in default lang and lang " +
                    "with '" + langTag +"' language tag.");
        }
        return new Result<>(phrase);
    }

    public static Result<String> getButtonByValue(String langTag, String buttonValue){
        throwExceptionIfNotInitialized();

        Language lang = repository.getLanguageByLanguageTag(langTag);

        throwExceptionIfLanguageIsNull(lang, langTag);

        String button = lang.buttons().getKey(buttonValue);
        if (button == null){
            button = repository.getDefaultLang().buttons().getKey(buttonValue);
            if (button != null){
                return new Result<>(button);
            }
            return new Result<>("not_found", "Button not found in default lang and lang " +
                         "with '" + langTag +"' language tag.");
        }
        return new Result<>(button);
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
}
