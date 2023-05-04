package hulio13.notionAlarm.telegramBot.inputHandlers.utils;

import java.util.ArrayList;

public class StringsToListsConverter {
    public static ArrayList<ArrayList<String>>  convert(int maxElementsInRow, String... strings){
        ArrayList<ArrayList<String>>  keyboard = new ArrayList<>();

        if (strings.length < maxElementsInRow){
            maxElementsInRow = strings.length;
        }

        int rowsCount = strings.length/maxElementsInRow +
                (strings.length % maxElementsInRow != 0 ? 1 : 0);

        int currIndexOfString = 0;

        boolean isFinished = false;

        for (int i = 0; i < rowsCount; i++){
            keyboard.add(new ArrayList<>());
            for (int j = 0; j < maxElementsInRow; j++){
                keyboard.get(i).add(strings[currIndexOfString]);
                if (currIndexOfString == strings.length - 1){
                    isFinished = true;
                    break;
                }
                currIndexOfString++;
            }
            if (isFinished) break;
        }

        return keyboard;
    }
}
