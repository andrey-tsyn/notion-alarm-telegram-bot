package hulio13.notionAlarm.database.jsonDb.repositories;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.io.UserToJsonSaver;
import hulio13.notionAlarm.database.jsonDb.providers.UserJsonSerializationProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class JsonUserRepository implements UserRepository {
    private final List<User> users;
    private final UserToJsonSaver saver;

    public JsonUserRepository(List<User> users, String pathToFolder) {
        this.users = users;
        this.saver = new UserToJsonSaver(pathToFolder, this, new UserJsonSerializationProvider());
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
        saver.save(user);
    }

    @Override
    public void addUser(User user) {
        synchronized (users){
            users.add(user);
        }
        saver.save(user);
    }
}
