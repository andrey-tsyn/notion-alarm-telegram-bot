package hulio13.notionAlarm.core.notifications;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.core.entities.notifications.RecurTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: Rename эту шнягу шняжную
public final class NotificationUpdater {
    private int intervalInHours;
    final private UserRepository repository;
    final public Notifier notifier;
    private LocalDateTime nextUpdateTime;

    public NotificationUpdater(int intervalInHours, UserRepository repository, Notifier notifier) {
        this.intervalInHours = intervalInHours;
        this.repository = repository;
        this.notifier = notifier;
        this.nextUpdateTime = LocalDateTime.now();
    }

    public void update(){
        notifier.update();
        if (nextUpdateTime.isBefore(LocalDateTime.now())){
            nextUpdateTime = LocalDateTime.now().plusHours(intervalInHours);
            List<User> users = repository.getUsers(user -> user.getNotifications().size() > 0);
            List<RecurTask> recurTasks = new ArrayList<>();
            for (var user :
                    users) {
                recurTasks.addAll(user.getNotifications().stream()
                        .filter(n -> n.getNotificationTime().isBefore(nextUpdateTime)).toList());
            }
            notifier.setNotifications(recurTasks);
        }
    }

    public void addNotification(RecurTask recurTask){
        User user = recurTask.getUser();
        if (!user.getNotifications().contains(recurTask))
            user.getNotifications().add(recurTask);

        if(recurTask.getNotificationTime().isBefore(nextUpdateTime))
            notifier.addNotification(recurTask);

        repository.updateUser(user);
    }
}