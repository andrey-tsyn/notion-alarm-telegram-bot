package hulio13.notionAlarm.telegramBot.localization;

import java.util.Map;
import java.util.Set;

// TODO: TEST BEFORE USING IN BOT
public final class VariablesInPhraseInserter {
    static public String insert(String phrase, Map<String, Object> vars){
        Set<String> keys = vars.keySet();

        for (var key :
                keys) {
            phrase = phrase.replace("{{" + key + "}}", vars.get(key).toString());
        }

        return phrase;
    }
}
