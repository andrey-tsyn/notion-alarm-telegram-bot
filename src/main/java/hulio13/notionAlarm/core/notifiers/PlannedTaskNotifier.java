package hulio13.notionAlarm.core.notifiers;

import hulio13.notionAlarm.core.abstractions.PlannedTaskListener;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlannedTaskNotifier {
    private final Map<String, List<PlannedTaskListener>> listeners;

    public PlannedTaskNotifier(String... eventTypes) {
        this.listeners = new HashMap<>();
        for (var eventType :
                eventTypes) {
            listeners.put(eventType, new ArrayList<>());
        }
    }

    public void subscribe(PlannedTaskListener listener, String... eventTypes) {
        for (var eventType :
                eventTypes) {
            if (!listeners.containsKey(eventType)) {
                throw new IllegalArgumentException("Event '" + eventType + "' does not exist.");
            }
            listeners.get(eventType).add(listener);
        }
    }

    public void unsubscribe(String eventType, PlannedTaskListener listener) {
        listeners.get(eventType).remove(listener);
    }

    public void notify(String eventType, List<PlannedTask> states) {
        for (var state :
                states) {
            notify(eventType, state);
        }
    }

    public void notify(String eventType, PlannedTask plannedTask) {
        for (var listener :
                listeners.get(eventType)) {
            listener.call(plannedTask.getUser(), plannedTask);
        }
    }
}