package hulio13.notionAlarm.core.emptyImplementations;

import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public final class EmptyPlannedTaskPlatform implements PlannedTaskPlatform {
    @Override
    public String getId() {
        return "empty";
    }

    @Override
    public boolean checkIsTaskNeedsUpdate(PlannedTask plannedTask) {
        return false;
    }

    @Override
    public void updateTask(PlannedTask plannedTask) {

    }
}
