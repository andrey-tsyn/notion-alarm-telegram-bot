package hulio13.notionAlarm.core.registrars;

import hulio13.notionAlarm.core.emptyImplementations.EmptyPlannedTaskPlatform;
import hulio13.notionAlarm.core.repositories.PlannedTaskPlatformRepository;

public class PlannedTaskPlatformRegistrar {
    static public void registerPlatforms(){
        // TODO: add notion platform
        PlannedTaskPlatformRepository.addPlatform(new EmptyPlannedTaskPlatform().getId(), new EmptyPlannedTaskPlatform());
    }
}