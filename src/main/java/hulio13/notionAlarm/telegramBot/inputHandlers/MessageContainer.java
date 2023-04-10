package hulio13.notionAlarm.telegramBot.inputHandlers;

import java.util.ArrayList;

public class MessageContainer {
    private final String msg;
    private final ArrayList<ArrayList<String>>  stringKeyboard;

    public MessageContainer(String msg, ArrayList<ArrayList<String>>  stringKeyboard) {
        this.msg = msg;
        this.stringKeyboard = stringKeyboard;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<ArrayList<String>>  getStringKeyboard() {
        return stringKeyboard;
    }
}