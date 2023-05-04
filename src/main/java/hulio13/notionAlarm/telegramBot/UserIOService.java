package hulio13.notionAlarm.telegramBot;

import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandlersRepository;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.inputHandlers.handlers.builders.WrappedInputHandlerBuilder;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;
import hulio13.notionAlarm.telegramBot.tgUserProperties.database.TgUserPropertiesRepository;

public final class UserIOService {
    private static String defaultLanguage = "none";

    private static String localizationHandlerId;

    private static boolean useLocalization = true;

    private static boolean useLogger = true;
    private final TgUserProperties properties;
    private final TgUserPropertiesRepository repository;

    public UserIOService(String telegramId) {
        repository = ServiceRegistry.getService(TgUserPropertiesRepository.class);
        properties = createOrGetProperties(telegramId);

        if (properties.getLocale().equals("none")) {
            properties.setHandlerId(localizationHandlerId);
        }
    }

    public static void setUseLocalization(boolean useLocalization) {
        UserIOService.useLocalization = useLocalization;
    }

    public static void setLocalizationHandlerId(String localizationHandlerId) {
        UserIOService.localizationHandlerId = localizationHandlerId;
    }

    public static void setUseLogger(boolean useLogger) {
        UserIOService.useLogger = useLogger;
    }

    public static void setDefaultLanguage(String defaultLanguage) {
        UserIOService.defaultLanguage = defaultLanguage;
    }

    public void processInput(String input) {
        InputHandler handler = getHandler();

        handler.processInput(input, properties);
    }

    private TgUserProperties createOrGetProperties(String telegramId) {
        TgUserProperties prop = repository.get(
                p -> p.telegramId.equals(telegramId)
        );

        if (prop == null) {
            prop = new TgUserProperties(telegramId, telegramId, defaultLanguage);
            repository.add(prop);
        }

        return prop;
    }

    public MessageContainer getMessageForUser() {
        InputHandler handler = getHandler();

        return handler.getMessageForUser();
    }

    private InputHandler getHandler() {
        InputHandler handler = InputHandlersRepository.getHandlerById(
                properties.getHandlerId());

        var builder = new WrappedInputHandlerBuilder(handler, properties);

        if (useLocalization && !properties.getLocale().equals("none")) builder.wrapWithLocalization();
        if (useLogger) builder.wrapWithLogger();

        return builder.getHandler();
    }
}
