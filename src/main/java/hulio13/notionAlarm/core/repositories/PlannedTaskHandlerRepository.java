package hulio13.notionAlarm.core.repositories;

import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskHandler;

import java.util.HashMap;

public final class PlannedTaskHandlerRepository {
    static private final HashMap<String, PlannedTaskHandler> handlers = new HashMap<>();

    static public void addHandler(String id, PlannedTaskHandler platform){
        handlers.put(id, platform);
    }

    static public PlannedTaskHandler getHandlerById(String id){
        return handlers.get(id);
    }
}
