package hulio13.notionAlarm.core.notifications;

import hulio13.notionAlarm.core.abstractions.NotificationListener;
import hulio13.notionAlarm.core.entities.notifications.RecurTask;

import java.util.*;

public final class NotificationManager {
    private List<NotificationListener> listeners;

    public NotificationManager() {
        this.listeners = new LinkedList<>();
    }

    public void subscribe(NotificationListener listener){
        listeners.add(listener);
    }

    public void unsubscribe(NotificationListener listener){
        listeners.remove(listener);
    }

    public void notify(RecurTask recurTask){
        for (var listener :
                listeners) {
            listener.call(recurTask.getUser(), recurTask);
        }
    }
}
