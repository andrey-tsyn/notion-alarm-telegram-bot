package hulio13.notionAlarm.core.notifications;

import hulio13.notionAlarm.core.entities.notifications.RecurTask;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public final class Notifier {
    public final NotificationManager event;
    private PriorityQueue<RecurTask> recurTasks;
    private LocalDateTime nextNotificationTime;

    public Notifier(List<RecurTask> recurTasks, LocalDateTime nextUpdateTime) {
        setNotifications(recurTasks);
        this.nextNotificationTime = this.recurTasks.peek().getNotificationTime();
        event = new NotificationManager();
    }

    public void update(){
        if (nextNotificationTime.isBefore(LocalDateTime.now())){
            event.notify(recurTasks.poll());
            nextNotificationTime = recurTasks.size() > 0 ? recurTasks.peek().getNotificationTime()
                    : LocalDateTime.MAX;
        }
    }

    public void setNotifications(List<RecurTask> recurTasks){
        this.recurTasks = new PriorityQueue<>(recurTasks);
    }

    public void addNotification(RecurTask recurTask){
        recurTasks.add(recurTask);

    }
}
