package hulio13.notionAlarm.core.entities;

import com.fasterxml.jackson.annotation.*;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;

import java.util.*;

public final class User {
    public final String telegramId;
    private String notionToken;
    @JsonManagedReference
    private final List<PlannedTask> plannedTasks;

    public User(String telegramId) {
        this.telegramId = telegramId;
        this.plannedTasks = new LinkedList<>();
    }

    @JsonCreator
    public User(@JsonProperty("telegramId") String telegramId, @JsonProperty("notionToken") String notionToken, @JsonProperty("resourceStates") List<PlannedTask> plannedTasks){
        this.telegramId = telegramId;
        this.plannedTasks = plannedTasks;
        this.notionToken = notionToken;
    }

    public User (String telegramId, String notionToken){
        this(telegramId, notionToken, new LinkedList<>());
    }
    public String getNotionToken() {
        return notionToken;
    }

    public void setNotionToken(String notionToken) {
        this.notionToken = notionToken;
    }

    public void addPlannedTask(PlannedTask plannedTask){
        plannedTasks.add(plannedTask);
    }
    public void addPlannedTasks(List<PlannedTask> plannedTasks) {this.plannedTasks.addAll(plannedTasks);}

    public List<PlannedTask> getPlannedTasks(){
        return Collections.unmodifiableList(plannedTasks);
    }
}
