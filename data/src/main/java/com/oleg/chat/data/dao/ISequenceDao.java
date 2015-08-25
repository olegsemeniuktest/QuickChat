package com.oleg.chat.data.dao;

import com.oleg.chat.data.entities.impl.Sequence;
import com.oleg.chat.data.utils.exceptions.RuntimeDBException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by Oleg Semeniuk.
 */
public interface ISequenceDao {

    void insert(Sequence sequence) throws DuplicateKeyException;

    Sequence get(String key);

    Long getNextSequenceId(String key);

}
