package hulio13.notionAlarm.core.abstractions;

import hulio13.notionAlarm.core.entities.User;

import java.util.List;
import java.util.function.Predicate;

public interface UserRepository {
    List<User> getUsers();
    List<User> getUsers(Predicate<User> predicate);
    User getUserByTelegramId(String telegramId);
    boolean removeUser(User user);
    void updateUser(User user);
    void addUser(User user);
}
