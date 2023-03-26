package hulio13.notionAlarm.notion;

import hulio13.notionAlarm.core.Result;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

import java.time.LocalDateTime;

public class NotionPlannedTaskPlatform implements PlannedTaskPlatform {
    static private final String id = "notion";
    @Override
    public String getId() {
        return id;
    }

    @Override
    public Result<Boolean> checkIsTaskNeedsUpdate(PlannedTask plannedTask) {
        Result<LocalDateTime> lastEditTime = NotionDatabaseTimeProvider.getLastEditedDate(plannedTask);

        if (!lastEditTime.getIsSuccess()){
            return new Result<>(false, false, lastEditTime.getError(), lastEditTime.getMessage());
        }

        synchronized (plannedTask){
            if(lastEditTime.getObject().plusMinutes(plannedTask.getIntervalToCheckInMinutes()).isBefore(LocalDateTime.now())){
                return new Result<>(true, false, null, null);
            }
            return new Result<>(true, true, null, null);
        }
    }
}