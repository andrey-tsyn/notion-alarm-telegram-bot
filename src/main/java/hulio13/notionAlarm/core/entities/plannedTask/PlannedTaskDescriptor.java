package hulio13.notionAlarm.core.entities.plannedTask;

import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

public final class PlannedTaskDescriptor {
    public final String linkToDatabase;
    private PlannedTaskPlatform platform;

    public PlannedTaskDescriptor(String linkToDatabase) {
        this.linkToDatabase = linkToDatabase;
    }

    public PlannedTaskDescriptor(String linkToDatabase, PlannedTaskPlatform platform) {
        this(linkToDatabase);
        this.platform = platform;
    }
}
