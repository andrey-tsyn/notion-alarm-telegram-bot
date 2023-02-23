package hulio13.notionAlarm.core.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

public class ResourceState {
    @JsonBackReference
    private User user;
    public final String resourceName;
    public final String linkToResource;
    private String description;
    private int intervalToCheckInMinutes;
    private LocalDateTime nextResourceUpdateTime;

    public ResourceState(User user, String resourceName, String linkToResource, String description, int intervalToCheckInMinutes, LocalDateTime nextResourceUpdateTime) {
        this.user = user;
        this.resourceName = resourceName;
        this.linkToResource = linkToResource;
        this.description = description;
        this.intervalToCheckInMinutes = intervalToCheckInMinutes;
        this.nextResourceUpdateTime = nextResourceUpdateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getIntervalToCheckInMinutes() {
        return intervalToCheckInMinutes;
    }

    public LocalDateTime getNextResourceUpdateTime() {
        return nextResourceUpdateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIntervalToCheckInMinutes(int intervalToCheckInMinutes) {
        this.intervalToCheckInMinutes = intervalToCheckInMinutes;
    }

    public void moveNextResourceUpdateTime() {
        this.nextResourceUpdateTime = LocalDateTime.now().plusMinutes(intervalToCheckInMinutes);
    }

    public void moveNextResourceUpdateTime(int intervalInMinutes) {
        this.nextResourceUpdateTime = LocalDateTime.now().plusMinutes(intervalInMinutes);
    }

    public User getUser(){return user;}

    public void setUserIfNull(User user){
        if (this.user != null){
            this.user = user;
        }
    }
}
