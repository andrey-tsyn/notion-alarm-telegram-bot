package hulio13.notionAlarm.core.entities.plannedTask.abstraction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import hulio13.notionAlarm.database.jsonDb.serialization.PlannedTaskPlatformDeserializer;
import hulio13.notionAlarm.database.jsonDb.serialization.PlannedTaskPlatformSerializer;

@JsonSerialize(using = PlannedTaskPlatformSerializer.class, as=String.class)
@JsonDeserialize(using = PlannedTaskPlatformDeserializer.class)
public interface PlannedTaskPlatform {
    String getId();
    boolean checkIsTaskNeedsUpdate(PlannedTask plannedTask);
    void updateTask(PlannedTask plannedTask);
}
