package hulio13.notionAlarm.core.registrars;

import hulio13.notionAlarm.core.emptyImplementations.EmptyPlannedTaskPlatform;
import hulio13.notionAlarm.core.repositories.PlannedTaskPlatformRepository;
import hulio13.notionAlarm.notion.NotionPlannedTaskPlatform;

public final class PlannedTaskPlatformRegistrar {
    static public void registerPlatforms() {
        PlannedTaskPlatformRepository.addPlatform(new EmptyPlannedTaskPlatform().getId(), new EmptyPlannedTaskPlatform());
        PlannedTaskPlatformRepository.addPlatform(new NotionPlannedTaskPlatform().getId(), new NotionPlannedTaskPlatform());
    }
}