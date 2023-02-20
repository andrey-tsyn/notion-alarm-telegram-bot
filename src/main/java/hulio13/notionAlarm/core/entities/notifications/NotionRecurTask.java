package hulio13.notionAlarm.core.entities.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import hulio13.notionAlarm.core.entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

public final class NotionRecurTask extends RecurTask {
    final private String linkToNotionDatabase;

    public NotionRecurTask(@JsonProperty("user") User user, @JsonProperty("description") String description,
                           @JsonProperty("timeIntervalInMinutes") int timeIntervalInMinutes,
                           @JsonProperty("notificationTime") LocalDateTime notificationTime,
                           @JsonProperty("uuid") UUID uuid,
                           @JsonProperty("linkToNotionDatabase") String linkToNotionDatabase) {
        super(user, description, timeIntervalInMinutes, notificationTime);
        this.linkToNotionDatabase = linkToNotionDatabase;
    }

    public String getLinkToNotionDatabase() {
        return linkToNotionDatabase;
    }
}
