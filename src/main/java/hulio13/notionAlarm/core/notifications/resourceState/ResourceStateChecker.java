package hulio13.notionAlarm.core.notifications.resourceState;

import hulio13.notionAlarm.core.entities.ResourceState;

import java.time.LocalDateTime;
import java.util.List;

public class ResourceStateChecker {
    static public List<ResourceState> getResourceStatesToUpdate(List<ResourceState> resourceStates){
        return resourceStates.stream()
                .filter(rs -> rs.getNextResourceUpdateTime().isAfter(LocalDateTime.now()))
                .toList();
    }
}
