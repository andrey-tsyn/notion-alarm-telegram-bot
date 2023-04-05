package hulio13.notionAlarm.database.jsonDb.repositories;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import hulio13.notionAlarm.database.jsonDb.io.UserJsonSaver;
import hulio13.notionAlarm.database.jsonDb.serialization.UserJsonSerialization;
import hulio13.notionAlarm.exceptions.NotInitializedException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class JsonUserRepository implements UserRepository {
    private static volatile JsonUserRepository instance;

    public static JsonUserRepository getInstance(List<User> users,
                                                 String pathToFolder) {
        JsonUserRepository result = instance;
        if (result != null){
            return result;
        }
        synchronized (JsonUserRepository.class){
            if (instance == null){
                instance = new JsonUserRepository(users, pathToFolder);
            }
            return instance;
        }
    }

    public static JsonUserRepository getInstance(){
        synchronized (instance){
            if (instance != null) return instance;
            throw new NotInitializedException(
                    "Call 'getInstance' method with args first");
        }
    }

    private final List<User> users;
    private final JsonSaver saver;

    private JsonUserRepository(List<User> users, String pathToFolder) {
        this.users = users;
        this.saver = new UserJsonSaver(pathToFolder, this, new UserJsonSerialization());
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
            Optional<User> user = users.stream().filter(predicate).findFirst();
            return user.isPresent() ? user.get() : null;
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
            User listUser = get(u -> u.id.equals(user.id));

            if (user != listUser){
                users.remove(listUser);
                users.add(user);
            }
        }
        try {
            saver.save(user, user.id);
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
            saver.save(user, user.id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
