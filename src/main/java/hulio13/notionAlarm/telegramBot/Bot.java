package hulio13.notionAlarm.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public final class Bot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "kozirni_notion_alarm_bot";

    public Bot(String token) {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update) {
        var button1 = KeyboardButton.builder()
                .text("Button")
                .build();

        var button2 = KeyboardButton.builder()
                .text("'Вназад'")
                .build();

        var button3 = KeyboardButton.builder().text("Button ponizhe").requestContact(true).build();

        var keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(new KeyboardRow(List.of(button1, button2)))
                .keyboardRow(new KeyboardRow(List.of(button3)))
                .build();

        try {
            execute(
                    SendMessage.builder()
                            .text("Some text")
                            .chatId(update.getMessage().getChatId())
                            .replyMarkup(keyboard)
                            .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }
}