package hulio13.notionAlarm.core.entities;

import com.fasterxml.jackson.annotation.*;
import hulio13.notionAlarm.core.entities.notifications.*;

import java.util.*;

public final class User {
    public final String telegramId;
    private String notionToken;
    @JsonManagedReference
    private final List<RecurTask> recurTasks;

    public User(String telegramId) {
        this.telegramId = telegramId;
        this.recurTasks = new LinkedList<>();
    }

    @JsonCreator
    public User(@JsonProperty("telegramId") String telegramId, @JsonProperty("notionToken") String notionToken, @JsonProperty("notifications") List<RecurTask> recurTasks){
        this.telegramId = telegramId;
        this.recurTasks = recurTasks;
        this.notionToken = notionToken;
    }

    public User (String telegramId, List<RecurTask> recurTasks){
        this.telegramId = telegramId;
        this.recurTasks = recurTasks;
    }
    public String getNotionToken() {
        return notionToken;
    }

    public void setNotionToken(String notionToken) {
        this.notionToken = notionToken;
    }

    public void addNotification(RecurTask recurTask){
        recurTasks.add(recurTask);
    }
    public void addNotifications(List<RecurTask> recurTasks) {this.recurTasks.addAll(recurTasks);}

    public List<RecurTask> getNotifications(){
        return Collections.unmodifiableList(recurTasks);
    }

    public boolean tryRemoveNotification(UUID uuid){
        for (var notification :
                recurTasks) {
            if (notification.getUuid() == uuid) return true;
        }
        return false;
    }

    public RecurTask getNotificationByUuid(UUID uuid){
        for (var notification :
                recurTasks) {
            if(notification.getUuid() == uuid) return notification;
        }
        return null;
    }
}
