package hulio13.notionAlarm.telegramBot.inputHandlers.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class KeyboardConverter {
    public static ReplyKeyboardMarkup fromStringToTelegram(
            ArrayList<ArrayList<String>> strKeyboard){
        var builder = ReplyKeyboardMarkup.builder();

        for (var strRow : strKeyboard) {
            KeyboardRow row = new KeyboardRow();
            for (var strBtn : strRow) {
                row.add(new KeyboardButton(strBtn));
            }
            builder.keyboardRow(row);
        }

        return builder.build();
    }
}
