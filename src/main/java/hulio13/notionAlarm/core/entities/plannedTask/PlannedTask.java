package hulio13.notionAlarm.core.entities.plannedTask;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hulio13.notionAlarm.core.entities.User;

import java.time.LocalDateTime;

public final class PlannedTask {
    @JsonBackReference
    private User user;
    private PlannedTaskDescriptor plannedTaskDescriptor;
    private String notificationText;
    private int intervalToCheckInMinutes;
    private LocalDateTime nextResourceUpdateTime;

    public PlannedTask(User user, PlannedTaskDescriptor plannedTaskDescriptor,
                       String notificationText, int intervalToCheckInMinutes, LocalDateTime nextResourceUpdateTime) {
        this.user = user;
        this.plannedTaskDescriptor = plannedTaskDescriptor;
        this.notificationText = notificationText;
        this.intervalToCheckInMinutes = intervalToCheckInMinutes;
        this.nextResourceUpdateTime = nextResourceUpdateTime;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public int getIntervalToCheckInMinutes() {
        return intervalToCheckInMinutes;
    }

    public LocalDateTime getNextUpdateTime() {
        return nextResourceUpdateTime;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public void setIntervalToCheckInMinutes(int intervalToCheckInMinutes) {
        this.intervalToCheckInMinutes = intervalToCheckInMinutes;
    }

    public void setUpdateTimeToNull(){
        nextResourceUpdateTime = null;
    }

    public void moveNextUpdateTime() {
        this.nextResourceUpdateTime = LocalDateTime.now().plusMinutes(intervalToCheckInMinutes);
    }

    public void moveNextUpdateTime(int intervalInMinutes) {
        this.nextResourceUpdateTime = LocalDateTime.now().plusMinutes(intervalInMinutes);
    }

    public User getUser(){return user;}

    public void setUserIfNull(User user){
        if (this.user != null){
            this.user = user;
        }
    }
}
