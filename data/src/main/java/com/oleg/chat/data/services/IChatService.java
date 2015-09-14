package com.oleg.chat.data.services;

import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.chats.Chat;
import com.oleg.chat.data.entities.impl.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IChatService {

    void save(Chat entity);

    void remove(long id);

    Chat get(long id);

    Collection<Chat> getPublicChats();

    Collection<Chat> getPrivateChats(IUser user);

    List<User> getActiveUsers(long chatId);

    boolean isUserHasAccess(long chatId, IUser user);

}
