package com.oleg.chat.data.dao;

import com.oleg.chat.data.entities.impl.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.List;

/**
 * Created by Oleg Semeniuk.
 */
public interface IUserDao {

    void insertOrUpdate(User entity);

    User get(long id);

    Collection<User> getAll();

    boolean remove(long id);

    User findByNickname(String nickname);

    boolean existNickname(String nickname);
}
