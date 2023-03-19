package hulio13.notionAlarm.telegramBot.inputHandlers;

import java.util.List;

public class MessageContainer {
    private final String userId;
    private String msg;
    private final List<List<String>> stringKeyboard;

    public MessageContainer(String userId, String msg, List<List<String>> stringKeyboard) {
        this.userId = userId;
        this.msg = msg;
        this.stringKeyboard = stringKeyboard;
    }

    public String getUserId() {
        return userId;
    }

    public String getMsg() {
        return msg;
    }

    public List<List<String>> getStringKeyboard() {
        return stringKeyboard;
    }
}