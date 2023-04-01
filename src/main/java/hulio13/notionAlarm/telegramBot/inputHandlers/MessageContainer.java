package hulio13.notionAlarm.telegramBot.inputHandlers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MessageContainer {
    private String msg;
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