package hulio13.notionAlarm.core.abstractions;

import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import hulio13.notionAlarm.core.entities.User;

public interface PlannedTaskListener {
    void call(User user, PlannedTask plannedTask);
}
