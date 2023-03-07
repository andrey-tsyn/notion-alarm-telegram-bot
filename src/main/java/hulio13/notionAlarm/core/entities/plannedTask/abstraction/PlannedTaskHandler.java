package hulio13.notionAlarm.core.entities.plannedTask.abstraction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTaskDescriptor;
import hulio13.notionAlarm.database.jsonDb.serialization.PlannedTaskHandlerDeserializer;
import hulio13.notionAlarm.database.jsonDb.serialization.PlannedTaskHandlerSerializer;

@JsonSerialize(using = PlannedTaskHandlerSerializer.class, as=String.class)
@JsonDeserialize(using = PlannedTaskHandlerDeserializer.class)
public interface PlannedTaskHandler {
    String getId();
    boolean isNeedsUpdate(PlannedTaskDescriptor descriptor);
}
