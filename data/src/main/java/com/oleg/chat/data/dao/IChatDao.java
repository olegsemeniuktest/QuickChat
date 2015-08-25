package com.oleg.chat.data.dao;

import com.mongodb.WriteResult;
import com.oleg.chat.data.entities.impl.Chat;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.List;

/**
 * Created by Oleg Semeniuk.
 */
public interface IChatDao {

    void insertOrUpdate(Chat entity);

    Chat get(long id);

    Collection<Chat> getAll();

    boolean remove(long id);

}
