package hulio13.notionAlarm.core.entities.plannedTask;

import com.fasterxml.jackson.annotation.JsonProperty;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskHandler;

public final class PlannedTaskDescriptor {
    public final String linkToDatabase;

    public final PlannedTaskHandler handler;

    public PlannedTaskDescriptor(@JsonProperty("linkToResource") String linkToResource,
                                 @JsonProperty("handler") PlannedTaskHandler handler) {
        this.linkToDatabase = linkToResource;
        this.handler = handler;
    }
}
