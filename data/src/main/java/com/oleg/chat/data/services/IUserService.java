package com.oleg.chat.data.services;

import com.oleg.chat.data.domain.User;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IUserService {

    void insert(User entity);

    void saveOrUpdate(User entity);

    void remove(long id);

    User getByNickName(String nickname);
}
