package hulio13.notionAlarm.core.abstractions;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Repository<T> {
    void forEach(Consumer<T> consumer);

    T get(Predicate<T> predicate);

    boolean remove(Predicate<T> predicate);

    void update(T obj);

    void add(T obj);
}
