package hulio13.notionAlarm.core.repositories;

import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

import java.util.HashMap;

public final class PlannedTaskPlatformRepository {
    static private final HashMap<String, PlannedTaskPlatform> platforms = new HashMap<>();

    static public void addPlatform(String id, PlannedTaskPlatform platform){
        platforms.put(id, platform);
    }

    static public PlannedTaskPlatform getPlatformById(String id){
        return platforms.get(id);
    }
}
