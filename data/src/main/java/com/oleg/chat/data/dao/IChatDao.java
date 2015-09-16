package com.oleg.chat.data.dao;

import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.User;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Oleg Semeniuk.
 */
public interface IChatDao {

    void insertOrUpdate(AChat entity);

    Optional<AChat> get(long id);

    boolean remove(long id);

    Collection<AChat> getPublicChats();

    Collection<AChat> getPublicChatsPart(Date timeFrom, int partSize);

    Collection<AChat> getPrivateChats(long userId);

    Collection<AChat> getPrivateChats(long userId, Date timeFrom, int partSize);

    boolean isUserHasAccess(long chatId, long userId);

}
