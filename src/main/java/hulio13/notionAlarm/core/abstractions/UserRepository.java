package hulio13.notionAlarm.core.abstractions;

import hulio13.notionAlarm.core.entities.User;

public interface UserRepository extends Repository<User> {
    User getById(String id);
}
