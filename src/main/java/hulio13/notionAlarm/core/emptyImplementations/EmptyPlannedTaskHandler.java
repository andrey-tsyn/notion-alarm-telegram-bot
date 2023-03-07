package hulio13.notionAlarm.core.emptyImplementations;

import hulio13.notionAlarm.core.entities.plannedTask.PlannedTaskDescriptor;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskHandler;

public final class EmptyPlannedTaskHandler implements PlannedTaskHandler {
    @Override
    public String getId() {
        return "empty";
    }
}
