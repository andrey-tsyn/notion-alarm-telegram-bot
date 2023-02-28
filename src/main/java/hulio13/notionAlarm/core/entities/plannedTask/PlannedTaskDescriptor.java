package hulio13.notionAlarm.core.entities.plannedTask;

import com.fasterxml.jackson.annotation.JsonProperty;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public final class PlannedTaskDescriptor {
    public final String linkToDatabase;

    public final PlannedTaskPlatform platform;

    public PlannedTaskDescriptor(@JsonProperty("linkToDatabase") String linkToDatabase,
                                 @JsonProperty("platform") PlannedTaskPlatform platform) {
        this.linkToDatabase = linkToDatabase;
        this.platform = platform;
    }
}
