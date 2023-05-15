package hulio13.notionAlarm.configuration.configurators;

import hulio13.notionAlarm.configuration.annotations.Configuration;
import hulio13.notionAlarm.configuration.annotations.Value;
import hulio13.telegramBoot.Bot;
import hulio13.telegramBoot.inputHandlers.InputHandlerRegistrar;

@Configuration(defaultPath = "telegram")
public class TelegramConfigurator {
    @Value(path = "botName")
    private String botName;

    @Value(path = "token")
    private String token;

    public Bot getConfiguredBot(){
        return new Bot(token, botName);
    }

    public void configure(){
        InputHandlerRegistrar.registerHandlers();
    }
}
