package hulio13.notionAlarm;

import hulio13.notionAlarm.configuration.ConfigurationReader;
import hulio13.notionAlarm.telegramBot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        // Configuration
        Map<String, Object> configValues = ConfigurationReader.readConfigurationFile("config.json");
        String telegramToken;
        telegramToken = (String)configValues.get("telegramToken");

        // Bot start
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new Bot(telegramToken));
    }
}
