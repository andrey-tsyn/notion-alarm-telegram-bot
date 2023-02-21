package hulio13.notionAlarm.database.jsonDb;

import hulio13.notionAlarm.core.abstractions.UserRepository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.exceptions.EntityNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JsonUserRepository implements UserRepository {
    private List<User> users;
    private UserJsonHandler userJsonHandler;

    public JsonUserRepository(UserJsonHandler userJsonHandler) {
        this.userJsonHandler = userJsonHandler;
    }

    @Override
    public List<User> getUsers() {
        if (users != null) return users;

        List<String> telegramIds = null;
        try{
            Path path = Paths.get(userJsonHandler.FOLDER_NAME);
             telegramIds = Files.list(path)
                    .map(Path::toFile)
                    .filter(file -> file.getName().contains(".json"))
                    .map(f -> f.getName())
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            // TODO: some logging and exit
            System.exit(-1);
        }
        synchronized (users){
            for (var tgId :
                    telegramIds) {
                try{
                    users.add(userJsonHandler.deserializeFromFile(tgId));
                }
                catch (EntityNotFoundException e){
                    // TODO: some logging
                }
            }
        }

        return users;
    }

    @Override
    public List<User> getUsers(Predicate<User> predicate) {
        return getUsers().stream().filter(predicate).toList();
    }

    @Override
    public User getUserByTelegramId(String telegramId) {
        synchronized (users){
            User user = getUsers().stream().filter(u -> u.telegramId.equals(telegramId)).findFirst().get();
            return user;
        }
    }

    @Override
    public boolean removeUser(User user) {
        boolean isRemoved;
        synchronized (users){
            isRemoved = users.remove(user);
        }
        if (!isRemoved) return false;

        try {
            Files.delete(Paths.get(UserJsonHandler.FOLDER_NAME + user.telegramId + ".json"));
            return true;
        } catch (IOException e) {
            // TODO: some logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        userJsonHandler.serializeInFile(user.telegramId, user);
    }

    @Override
    public void addUser(User user) {
        synchronized (users){
            users.add(user);
        }
        userJsonHandler.serializeInFile(user.telegramId, user);
    }
}
