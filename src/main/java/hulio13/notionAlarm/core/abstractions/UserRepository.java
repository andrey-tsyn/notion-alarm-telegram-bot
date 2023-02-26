package hulio13.notionAlarm.core.abstractions;

import hulio13.notionAlarm.core.entities.User;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface UserRepository {
    void forEach(Consumer<User> consumer);

    User getUser(Predicate<User> predicate);

    boolean removeUser(User user);

    void updateUser(User user);

    void addUser(User user);
}
