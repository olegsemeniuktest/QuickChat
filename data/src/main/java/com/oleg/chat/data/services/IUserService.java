package com.oleg.chat.data.services;

import com.oleg.chat.data.entities.impl.User;

import java.util.Optional;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IUserService {

    void insert(User entity);

    void saveOrUpdate(User entity);

    void remove(long id);

    Optional<User> getByNickName(String nickname);
}
