package hulio13.notionAlarm.core.emptyImplementations;

import hulio13.notionAlarm.core.Result;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public final class EmptyPlannedTaskPlatform implements PlannedTaskPlatform {
    @Override
    public String getId() {
        return "empty";
    }

    @Override
    public Result<Boolean> checkIsTaskNeedsUpdate(PlannedTask plannedTask) {
        return null;
    }

    @Override
    public Result<Void> updateTask(PlannedTask plannedTask) {
        return null;
    }
}
