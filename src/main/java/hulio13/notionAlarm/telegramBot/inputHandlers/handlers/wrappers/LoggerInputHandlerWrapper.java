package hulio13.notionAlarm.telegramBot.inputHandlers.handlers.wrappers;

import hulio13.notionAlarm.telegramBot.inputHandlers.InputHandler;
import hulio13.notionAlarm.telegramBot.inputHandlers.MessageContainer;
import hulio13.notionAlarm.telegramBot.inputHandlers.handlers.wrappers.abstraction.InputHandlerWrapper;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class LoggerInputHandlerWrapper extends InputHandlerWrapper {
    private static Logger logger = LoggerFactory.getLogger("InputHandlerLogger");

    public LoggerInputHandlerWrapper(InputHandler inputHandler) {
        super(inputHandler);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void processInput(String input, TgUserProperties properties) {
        logger.debug(getInputHandler().getId() + "'s processInput method called by user with " + properties.telegramId
                + "telegram id.");
        try{
            super.processInput(input, properties);
        }
        catch (Exception e){
            logger.error("Error msg: " + e.getMessage() + ". ");
            logger.debug("Stacktrace: "  + ExceptionUtils.getStackTrace(e));
            throw e;
        }

    }

    @Override
    public MessageContainer getMessageForUser() {
        try{
            return super.getMessageForUser();
        }
        catch (Exception e){
            logger.error("Error msg: " + e.getMessage() + ". ");
            logger.debug("Stacktrace: "  + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }
}
