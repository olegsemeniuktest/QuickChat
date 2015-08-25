package com.oleg.chat.data.dao.impl.inmemory;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.IUserDao;
import com.oleg.chat.data.entities.impl.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.IN_MEMORY)
public class UserDao implements IUserDao {

    private final Map<Long, User> userMap = new HashMap<>();

    @Override
    public void insertOrUpdate(User entity) {
        userMap.put(entity.getId(), entity);
    }

    @Override
    public User get(long id) {
        return userMap.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public boolean remove(long id) {
        return userMap.remove(id) != null;
    }

    @Override
    public User findByNickname(String nickname) { //todo refactor with java 8
        User found = null;
        for (User user : userMap.values()) {
            if (user.getNickname().equals(nickname)) {
                found = user;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean existNickname(String nickname) {
        return userMap.values().stream().anyMatch(user -> user.getNickname().equals(nickname));
    }
}
