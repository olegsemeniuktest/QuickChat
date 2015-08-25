package com.oleg.chat.data.services;

import com.oleg.chat.data.entities.impl.Chat;
import com.oleg.chat.data.entities.impl.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IChatService<C extends Chat> {

    void save(C entity);

    void remove(long id);

    C get(long id);

    Collection<Chat> getAll();

    List<User> getActiveUsers(long chatId);

    boolean isUserHasAccess(long chatId, User user);

}
