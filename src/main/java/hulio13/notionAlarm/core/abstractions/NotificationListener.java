package hulio13.notionAlarm.core.abstractions;

import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.core.entities.notifications.RecurTask;

public interface NotificationListener {
    void call(User user, RecurTask recurTask);
}
