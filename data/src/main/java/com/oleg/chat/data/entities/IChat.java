package com.oleg.chat.data.entities;

import com.oleg.chat.data.entities.impl.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Oleg Semeniuk.
 */
public interface IChat {


    String getName();

    void setName(String name);

    boolean isPrivate();

    Collection<IUser> getActiveUsers();

    void addActiveUser(IUser user);

    void removeActiveUser(IUser user);

}
