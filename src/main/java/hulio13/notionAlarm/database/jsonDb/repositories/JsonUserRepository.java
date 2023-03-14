package hulio13.notionAlarm.database.jsonDb.repositories;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.io.UserToJsonSaver;
import hulio13.notionAlarm.database.jsonDb.serialization.UserJsonSerialization;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class JsonUserRepository implements UserRepository {
    private final List<User> users;
    private final UserToJsonSaver saver;

    public JsonUserRepository(List<User> users, String pathToFolder) {
        this.users = users;
        this.saver = new UserToJsonSaver(pathToFolder, this, new UserJsonSerialization());
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
    public User get(Predicate<User> predicate) {
        synchronized (users){
            User user = users.stream().filter(predicate).findFirst().get();
            return user;
        }
    }

    @Override
    public boolean remove(Predicate<User> predicate) {
        boolean isRemoved;
        synchronized (users){
            isRemoved = users.removeIf(predicate);
        }
        return isRemoved;
    }

    @Override
    public void update(User user) {
        synchronized (users){
            User listUser = get(u -> u.telegramId.equals(user.telegramId));

            if (user != listUser){
                users.remove(listUser);
                users.add(user);
            }
        }
        try {
            saver.save(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User user) {
        synchronized (users){
            users.add(user);
        }
        try {
            saver.save(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
