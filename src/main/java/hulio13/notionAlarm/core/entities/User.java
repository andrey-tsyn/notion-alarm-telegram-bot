package hulio13.notionAlarm.core.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class User {
    public final String id;
    private Map<String, String> tokens;
    @JsonManagedReference
    private final List<PlannedTask> plannedTasks;

    public User(String id) {
        this.id = id;
        this.plannedTasks = new LinkedList<>();
    }

    @JsonCreator
    public User(@JsonProperty("telegramId") String id, @JsonProperty("tokens") Map<String, String> tokens, @JsonProperty("plannedTasks") List<PlannedTask> plannedTasks) {
        this.id = id;
        this.plannedTasks = plannedTasks;
        this.tokens = tokens;
    }

    public User(String id, Map<String, String> tokens) {
        this(id, tokens, new LinkedList<>());
    }

    public String getTokenById(String id) {
        return tokens.get(id);
    }

    public void addToken(String id, String token) {
        tokens.put(id, token);
    }

    public void removeToken(String id) {
        tokens.remove(id);
    }

    public void addPlannedTask(PlannedTask plannedTask) {
        plannedTasks.add(plannedTask);
    }

    public void addPlannedTasks(List<PlannedTask> plannedTasks) {
        this.plannedTasks.addAll(plannedTasks);
    }

    public List<PlannedTask> getPlannedTasks() {
        return Collections.unmodifiableList(plannedTasks);
    }
}
