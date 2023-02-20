package hulio13.notionAlarm.telegramBot.localization;

import hulio13.notionAlarm.telegramBot.localization.exceptions.InvalidPhraseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class VariablesInPhraseInserter {
    static public String insert(String phrase, Map<String, String> vars) throws InvalidPhraseException{
        String[] splitedPhrase = phrase.split("\\{\\{\\|}}");
        Integer[] indexes = getVarIndexes(phrase);

        for (var index : indexes) {
            splitedPhrase[index] = vars.get(splitedPhrase[index]);
        }

        return String.join("", splitedPhrase);
    }

    static private Integer[] getVarIndexes(String phrase) throws InvalidPhraseException{
        List<Integer> indexes = new ArrayList<>();
        int currIndex = 0;
        boolean wasVar = false, isVarAtThatTime = false;

        for(int i = 0; i < phrase.length(); i++){
            if (phrase.charAt(i) == '{' && i + 1 < phrase.length()
            && phrase.charAt(i + 1) == '{'){
                i += 2;
                isVarAtThatTime = true;
                indexes.add(currIndex++);
            }

            if (phrase.charAt(i) == '}' && i + 1 < phrase.length()
                    && phrase.charAt(i + 1) == '}'){
                i += 2;
                isVarAtThatTime = false;
                currIndex++;
            }
        }

        if (isVarAtThatTime){
            throw new InvalidPhraseException("Localization template is not correct. Template: " + phrase);
        }

        return indexes.toArray(new Integer[0]);
    }
}
