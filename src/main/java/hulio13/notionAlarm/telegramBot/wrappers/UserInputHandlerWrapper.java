package hulio13.notionAlarm.telegramBot.wrappers;

import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.telegramBoot.inputHandlers.InputHandler;
import hulio13.telegramBoot.inputHandlers.wrappers.abstraction.InputHandlerWrapper;
import hulio13.telegramBoot.tgUserProperties.TgUserProperties;
import hulio13.notionAlarm.core.entities.User;

public class UserInputHandlerWrapper extends InputHandlerWrapper {
    public UserInputHandlerWrapper(InputHandler inputHandler, TgUserProperties properties) {
        super(inputHandler);

        if (properties.getPayload().get("user") == null) {
            JsonUserRepository repository = JsonUserRepository.getInstance();
            User user = repository.getById(properties.getUserId());

            if (user == null) {
                user = new User(properties.getUserId());
                repository.add(user);
            }

            properties.getPayload().put("user", user);
        }
    }
}
