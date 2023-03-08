package hulio13.notionAlarm.core.entities.plannedTask;

import com.fasterxml.jackson.annotation.JsonProperty;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public final class PlannedTaskDescriptor {
    public final String dataForAccess;

    public final PlannedTaskPlatform platform;

    public PlannedTaskDescriptor(@JsonProperty("dataForAccess") String dataForAccess,
                                 @JsonProperty("platform") PlannedTaskPlatform platform) {
        this.dataForAccess = dataForAccess;
        this.platform = platform;
    }
}
