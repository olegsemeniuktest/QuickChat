package com.oleg.chat.data.dao;

import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;

import java.util.Collection;

/**
 * Created by Oleg Semeniuk.
 */
public interface IChatDao {

    void insertOrUpdate(AChat entity);

    IChat get(long id);

    Collection<IChat> getAllPublic();

    Collection<IChat> getAllPrivate(IUser user);

    boolean remove(long id);

}
