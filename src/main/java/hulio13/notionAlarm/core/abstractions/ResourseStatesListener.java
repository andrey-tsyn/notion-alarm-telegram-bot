package hulio13.notionAlarm.core.abstractions;

import hulio13.notionAlarm.core.entities.ResourceState;
import hulio13.notionAlarm.core.entities.User;

public interface ResourseStatesListener {
    void call(User user, ResourceState state);
}
