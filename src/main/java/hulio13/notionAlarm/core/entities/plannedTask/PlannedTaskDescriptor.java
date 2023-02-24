package hulio13.notionAlarm.core.entities.plannedTask;

import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public final class PlannedTaskDescriptor {
    public final String linkToDatabase;
    public final PlannedTaskPlatform platform;

    public PlannedTaskDescriptor(String linkToDatabase, PlannedTaskPlatform platform) {
        this.linkToDatabase = linkToDatabase;
        this.platform = platform;
    }
}
