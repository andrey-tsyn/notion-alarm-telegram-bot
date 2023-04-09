package hulio13.notionAlarm.telegramBot.tgUserProperties.database.jsonDb;

import hulio13.notionAlarm.core.abstractions.Repository;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.jsonDb.repositories.JsonUserRepository;
import hulio13.notionAlarm.exceptions.NotInitializedException;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;
import hulio13.notionAlarm.telegramBot.tgUserProperties.database.TgUserPropertiesRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class JsonTgUserPropertiesRepository implements TgUserPropertiesRepository {
    private static volatile JsonTgUserPropertiesRepository instance;

    public static JsonTgUserPropertiesRepository getInstance(
            List<TgUserProperties> infos) {
        JsonTgUserPropertiesRepository result = instance;

        if (result != null){
            return result;
        }
        synchronized (JsonUserRepository.class){
            if (instance == null){
                instance = new JsonTgUserPropertiesRepository(infos);
            }
            return instance;
        }
    }

    public static JsonTgUserPropertiesRepository getInstance(){
        synchronized (JsonUserRepository.class){
            if (instance != null) return instance;
            throw new NotInitializedException(
                    "Call 'getInstance' method with args first");
        }
    }

    private final List<TgUserProperties> infos;

    private JsonTgUserPropertiesRepository(List<TgUserProperties> infos) {
        this.infos = infos;
    }

    public void forEach(Consumer<TgUserProperties> consumer){
        synchronized (infos){
            for (var info : infos) {
                consumer.accept(info);
            }
        }
    }

    @Override
    public TgUserProperties get(Predicate<TgUserProperties> predicate) {
        synchronized (infos){
            Optional<TgUserProperties> info = infos.stream()
                    .filter(predicate)
                    .findFirst();
            return info.isPresent() ? info.get() : null;
        }
    }

    @Override
    public boolean remove(Predicate<TgUserProperties> predicate) {
        synchronized (infos){
            return infos.removeIf(predicate);
        }
    }

    @Override
    public void update(TgUserProperties info) {
        synchronized (infos){
            TgUserProperties listInfo = get(i -> i.telegramId.equals(i.telegramId));

            if (info != listInfo){
                infos.remove(listInfo);
                infos.add(info);
            }
        }
    }

    public void add(TgUserProperties info){
        synchronized (infos) {
            infos.add(info);
        }
    }
}
