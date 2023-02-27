package hulio13.notionAlarm.core.providers;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import hulio13.notionAlarm.core.entities.User;

import java.util.ArrayList;
import java.util.List;

public final class PlannedTaskProvider {
    private final UserRepository userRepository;

    public PlannedTaskProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<PlannedTask> getPlannedTasks(){
        List<PlannedTask> plannedTasks = new ArrayList<>();

        userRepository.forEach(user -> {
                plannedTasks.addAll(user.getPlannedTasks());
        });

        return plannedTasks;
    }

}