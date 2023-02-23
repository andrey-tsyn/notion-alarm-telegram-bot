package hulio13.notionAlarm.core.notifications.resourceState;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.ResourceState;
import hulio13.notionAlarm.core.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ResourceStatesProvider {
    private final UserRepository userRepository;

    public ResourceStatesProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ResourceState> getResourceStates(){
        List<ResourceState> resourceStates = new ArrayList<>();

        List<User> users = userRepository.getUsers();

        for (var user :
                users) {
            resourceStates.addAll(user.getResourceStates());
        }

        return resourceStates;
    }
}