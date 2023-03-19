package hulio13.notionAlarm.telegramBot.tgUserProperties.database.jsonDb;

import hulio13.notionAlarm.core.abstractions.Repository;
import hulio13.notionAlarm.telegramBot.tgUserProperties.TgUserProperties;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class JsonTgUserPropertiesRepository implements Repository<TgUserProperties> {
    private final List<TgUserProperties> infos;

    public JsonTgUserPropertiesRepository(List<TgUserProperties> infos) {
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
            var info = infos.stream().filter(predicate).findFirst().get();
            return info;
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
