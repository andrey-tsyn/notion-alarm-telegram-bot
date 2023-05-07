package hulio13.notionAlarm.telegramBot.inputHandlers.wrappers;

import hulio13.notionAlarm.core.Result;
import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.inputHandlers.wrappers.abstraction.InputHandlerWrapper;
import hulio13.notionAlarm.telegramBot.localization.LocalizationService;
import hulio13.notionAlarm.telegramBot.localization.VariablesInPhraseInserter;
import hulio13.notionAlarm.exceptions.NotFoundException;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class LocalizationInputHandlerWrapper extends InputHandlerWrapper {
    private static final Logger logger = LoggerFactory.getLogger(LocalizationInputHandlerWrapper.class);
    private final Map<String, Object> vars;
    private final String languageTag;

    public LocalizationInputHandlerWrapper(InputHandler inputHandler, Map<String, Object> vars, String languageTag) {
        super(inputHandler);
        this.vars = vars;
        this.languageTag = languageTag;
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        Result<String> resultButton = LocalizationService.getButtonByValue(
                properties.getLocale(),
                input
        );

        if (resultButton.isSuccess()) {
            input = resultButton.object();
        }

        super.processInput(input, properties);
    }

    @Override
    public MessageContainer getMessageForUser() {
        MessageContainer messageContainer = super.getMessageForUser();
        String langTag = languageTag;

        Result<String> resultMsg = LocalizationService.getPhraseById(
                langTag,
                messageContainer.getMsg());

        if (!resultMsg.isSuccess() && !getInputHandler().HasNonLocalizableText()) {
            throw new NotFoundException(
                    resultMsg.error() + ": " + resultMsg.message()
            );
        }

        String message;

        if (resultMsg.object() == null &&
                getInputHandler().HasNonLocalizableText()) {
            message = messageContainer.getMsg();
        } else message = resultMsg.object();

        message = VariablesInPhraseInserter.insert(message, vars);

        ArrayList<ArrayList<String>> keyboard = messageContainer.getStringKeyboard();

        for (int i = 0; i < keyboard.size(); i++) {
            List<String> row = keyboard.get(i);

            for (int j = 0; j < row.size(); j++) {
                Result<String> resultBtn = LocalizationService.getButtonById(
                        langTag,
                        row.get(j));

                if (!resultBtn.isSuccess() && !getInputHandler().HasNonLocalizableText()) {
                    throw new NotFoundException(resultBtn.error() + ": "
                            + resultBtn.message());
                }

                if (resultBtn.object() == null &&
                        getInputHandler().HasNonLocalizableText()) {
                    String button = messageContainer
                            .getStringKeyboard()
                            .get(i)
                            .get(j);
                    row.set(j, button);
                } else row.set(j, resultBtn.object());
            }
        }

        MessageContainer localizedMessageContainer = new MessageContainer(
                message,
                keyboard
        );

        return localizedMessageContainer;
    }
}