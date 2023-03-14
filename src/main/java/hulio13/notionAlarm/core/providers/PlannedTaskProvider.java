package hulio13.notionAlarm.core.providers;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;

import java.util.ArrayList;
import java.util.List;

public final class PlannedTaskProvider {
    private final UserRepository repository;

    public PlannedTaskProvider(UserRepository repository) {
        this.repository = repository;
    }
    public List<PlannedTask> getPlannedTasks(){
        List<PlannedTask> plannedTasks = new ArrayList<>();

        repository.forEach(user -> {
                plannedTasks.addAll(user.getPlannedTasks());
        });

        return plannedTasks;
    }

}