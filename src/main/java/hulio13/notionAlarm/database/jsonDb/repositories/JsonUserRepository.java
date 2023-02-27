package hulio13.notionAlarm.database.jsonDb.repositories;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class JsonUserRepository implements UserRepository {
    private List<User> users;

    public JsonUserRepository(List<User> users) {
        this.users = users;
    }

    @Override
    public void forEach(Consumer<User> consumer) {
        synchronized (users){
            for (var user : users) {
                consumer.accept(user);
            }
        }
    }

    @Override
    public User getUser(Predicate<User> predicate) {
        synchronized (users){
            User user = users.stream().filter(predicate).findFirst().get();
            return user;
        }
    }

    @Override
    public boolean removeUser(User user) {
        boolean isRemoved;
        synchronized (users){
            isRemoved = users.remove(user);
        }
        return isRemoved;
    }

    @Override
    public void updateUser(User user) {
        synchronized (users){
            User listUser = getUser(u -> u.telegramId.equals(user.telegramId));

            if (user != listUser){
                users.remove(listUser);
                users.add(user);
            }
        }
    }

    @Override
    public void addUser(User user) {
        synchronized (users){
            users.add(user);
        }
    }
}
