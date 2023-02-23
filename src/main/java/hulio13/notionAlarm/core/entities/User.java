package hulio13.notionAlarm.core.entities;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

public final class User {
    public final String telegramId;
    private String notionToken;
    @JsonManagedReference
    private final List<ResourceState> resourceStates;

    public User(String telegramId) {
        this.telegramId = telegramId;
        this.resourceStates = new LinkedList<>();
    }

    @JsonCreator
    public User(@JsonProperty("telegramId") String telegramId, @JsonProperty("notionToken") String notionToken, @JsonProperty("resourceStates") List<ResourceState> resourceStates){
        this.telegramId = telegramId;
        this.resourceStates = resourceStates;
        this.notionToken = notionToken;
    }

    public User (String telegramId, List<ResourceState> resourceStates){
        this.telegramId = telegramId;
        this.resourceStates = resourceStates;
    }
    public String getNotionToken() {
        return notionToken;
    }

    public void setNotionToken(String notionToken) {
        this.notionToken = notionToken;
    }

    public void addNotification(ResourceState resourceState){
        resourceStates.add(resourceState);
    }
    public void addNotifications(List<ResourceState> resourceStates) {this.resourceStates.addAll(resourceStates);}

    public List<ResourceState> getResourceStates(){
        return Collections.unmodifiableList(resourceStates);
    }
}
