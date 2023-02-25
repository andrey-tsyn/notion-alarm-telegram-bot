package hulio13.notionAlarm.core.emptyImplementations;

import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public class EmptyPlannedTaskPlatform implements PlannedTaskPlatform {
    @Override
    public String getId() {
        return "Empty";
    }
}
