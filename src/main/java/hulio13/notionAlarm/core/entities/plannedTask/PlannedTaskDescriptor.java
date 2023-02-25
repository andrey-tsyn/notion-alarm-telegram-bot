package hulio13.notionAlarm.core.entities.plannedTask;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;
import hulio13.notionAlarm.database.jsonDb.serializers.PlannedTaskPlatformSerializer;

public final class PlannedTaskDescriptor {
    public final String linkToDatabase;

    public final PlannedTaskPlatform platform;

    public PlannedTaskDescriptor(@JsonProperty("linkToDatabase") String linkToDatabase,
                                 @JsonProperty("platform") PlannedTaskPlatform platform) {
        this.linkToDatabase = linkToDatabase;
        this.platform = platform;
    }
}
