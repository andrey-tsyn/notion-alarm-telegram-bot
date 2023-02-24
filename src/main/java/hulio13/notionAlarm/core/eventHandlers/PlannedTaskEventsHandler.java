package hulio13.notionAlarm.core.eventHandlers;

import hulio13.notionAlarm.core.notifiers.PlannedTaskNotifier;
import hulio13.notionAlarm.core.providers.PlannedTaskProvider;
import hulio13.notionAlarm.core.providers.UpdatablePlannedTaskProvider;

public final class PlannedTaskEventsHandler {
    private final PlannedTaskNotifier notifier;
    private final PlannedTaskProvider provider;

    public PlannedTaskEventsHandler(PlannedTaskNotifier notifier, PlannedTaskProvider provider) {
        this.notifier = notifier;
        this.provider = provider;
    }

    public void update(){
        notifyListenersWithTasksToUpdate();
    }

    private void notifyListenersWithTasksToUpdate(){
        var tasksToUpdate = UpdatablePlannedTaskProvider.getPlannedTasksToUpdate(provider.getPlannedTasks());
        for (var task :
                tasksToUpdate) {
            task.setUpdateTimeToNull();
        }
        notifier.notify(
                "update",
                tasksToUpdate
        );
    }
}
