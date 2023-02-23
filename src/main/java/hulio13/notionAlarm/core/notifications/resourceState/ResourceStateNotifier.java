package hulio13.notionAlarm.core.notifications.resourceState;

import hulio13.notionAlarm.core.abstractions.ResourseStatesListener;
import hulio13.notionAlarm.core.entities.ResourceState;
import hulio13.notionAlarm.core.notifications.resourceState.exceptions.IncorrectOperationException;

import java.util.*;

public final class ResourceStateNotifier {
    private Map<String, List<ResourseStatesListener>> listeners;
    private String[] eventTypes;

    public ResourceStateNotifier(String... eventTypes) {
        this.listeners = new HashMap<>();
        for (var eventType :
                eventTypes) {
            listeners.put(eventType, new ArrayList<>());
        }
        
        this.eventTypes = eventTypes;
    }

    public void subscribe(ResourseStatesListener listener, String... eventTypes){
        for (var eventType :
                eventTypes) {
            if (!listeners.containsKey(eventType)) {
                throw new IncorrectOperationException("Event '" + eventType + "' does not exist.");
            }
            listeners.get(eventType).add(listener);
        }
    }

    public void unsubscribe(ResourseStatesListener listener){
        listeners.remove(listener);
    }

    public void notify(String eventType, ResourceState resourceState){
        for (var listener :
                listeners.get(eventType)) {
            listener.call(resourceState.getUser(), resourceState);
        }
    }
}