package com.oleg.chat.data.services;

import com.oleg.chat.data.domain.Chat;
import com.oleg.chat.data.domain.User;

import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IChatService<C extends Chat> {

    void insert(C entity);

    void saveOrUpdate(C entity);

    void remove(long id);

    public C get(long id);

    public List<C> getAll();

    List<User> getActiveUsers(long chatId);

    boolean isUserHasAccess(long chatId, User user);

}
