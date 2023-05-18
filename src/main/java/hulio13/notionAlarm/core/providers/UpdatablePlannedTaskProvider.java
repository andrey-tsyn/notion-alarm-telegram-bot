package hulio13.notionAlarm.core.providers;

import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;

import java.time.LocalDateTime;
import java.util.List;

public final class UpdatablePlannedTaskProvider {
    static public List<PlannedTask> getPlannedTasksToUpdate(List<PlannedTask> plannedTasks) {
        var now = LocalDateTime.now();
        return plannedTasks.stream()
                .filter(pt -> pt.getNextResourceUpdateTime().isBefore(now)
                        && pt.getNextResourceUpdateTime() != null)
                .toList();
    }
}