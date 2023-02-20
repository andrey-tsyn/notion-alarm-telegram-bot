package hulio13.notionAlarm.core.entities.notifications;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hulio13.notionAlarm.core.entities.User;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.UUID;

public class RecurTask implements Comparable{
    @JsonBackReference
    private User user;
    final private UUID uuid;
    private String description;
    private int timeIntervalInMinutes;
    private LocalDateTime notificationTime;

    public RecurTask(User user, String description, int timeIntervalInMinutes, LocalDateTime notificationTime) {
        this.uuid = UUID.randomUUID();
        this.description = description;
        this.timeIntervalInMinutes = timeIntervalInMinutes;
        this.notificationTime = notificationTime;
        this.user = user;
    }

    @JsonCreator
    public RecurTask(@JsonProperty("user") User user, @JsonProperty("description") String description,
                     @JsonProperty("timeIntervalInMinutes") int timeIntervalInMinutes,
                     @JsonProperty("notificationTime") LocalDateTime notificationTime,
                     @JsonProperty("uuid") UUID uuid) {
        this.uuid = uuid;
        this.description = description;
        this.timeIntervalInMinutes = timeIntervalInMinutes;
        this.notificationTime = notificationTime;
        this.user = user;
    }
    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public int getTimeIntervalInMinutes() {
        return timeIntervalInMinutes;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public User getUser(){return user;}
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUserIfNull(User user) {
        if (this.user == null) this.user = user;
    }

    public void setTimeIntervalInMinutes(int timeIntervalInMinutes) {
        this.timeIntervalInMinutes = timeIntervalInMinutes;
    }

    public void updateNotificationTime() {
        notificationTime = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(timeIntervalInMinutes);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        if (!(o instanceof RecurTask)) throw new IllegalArgumentException();
        RecurTask recurTask = (RecurTask) o;
        if(this.getNotificationTime().isBefore(recurTask.getNotificationTime())) return -1;
        if(this.getNotificationTime().isAfter(recurTask.getNotificationTime())) return 1;
        return 0;
    }
}