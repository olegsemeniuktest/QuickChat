package com.oleg.chat.data.services;

import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;
import com.oleg.chat.data.entities.impl.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IChatService {

    void save(Chat entity);

    void remove(long id);

    IChat get(long id);

    Collection<AChat> getPublicChats();

    Collection<AChat> getPublicChatsPart(Date timeFrom, int partSize);

    Collection<AChat> getPrivateChats(IUser user);

    Collection<AChat> getPrivateChats(IUser user, Date timeFrom, int partSize);

    Collection<IUser> getActiveUsers(long chatId);

    boolean isUserHasAccess(long chatId, IUser user);

}
