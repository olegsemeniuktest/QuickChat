package com.oleg.chat.data.services;

import com.oleg.chat.data.entities.impl.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IPrivateChatService {

    List<User> getMembers(long chatId);

    void addMember(long chatId, User user);

    void addMembers(long chatId, Collection<User> users);

    void excludeMember(long chatId, User user);
}
