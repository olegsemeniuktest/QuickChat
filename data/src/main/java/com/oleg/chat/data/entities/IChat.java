package com.oleg.chat.data.entities;

import com.oleg.chat.data.entities.impl.Message;
import com.oleg.chat.data.entities.impl.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Oleg Semeniuk.
 */
public interface IChat {

    String getName();

    void setName(String name);

    List<Message> getMessages();

    void setMessages(List<Message> messages);

    boolean isPrivate();

    Collection<IUser> getActiveUsers();

    void addActiveUser(IUser user);

    void removeActiveUser(IUser user);

    Date getCreateTime();

    void setCreateTime(Date createTime);

}
