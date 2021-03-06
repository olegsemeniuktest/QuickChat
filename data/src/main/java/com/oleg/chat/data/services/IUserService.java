package com.oleg.chat.data.services;

import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.User;

import java.util.Optional;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IUserService {

    /**
     * Stores an object in the repository or update it, if the
     * object is already present.
     *
     * @param entity - object to be stored or updated
     */
    void save(User entity);

    Optional<User> getById(long id);

    Optional<User> getByNickName(String nickname);

    boolean isNicknameUsed(String nickname);

    void expireUser(IUser user);

    void remove(long id);
}
