package com.oleg.chat.data.dao.impl.db;

import com.mongodb.WriteResult;
import com.oleg.chat.data.entities.AEntity;
import com.oleg.chat.data.entities.impl.chats.AChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by oleg on 14.03.2015.
 */
public abstract class BaseDao<Entity extends AEntity> {

    private final Class<Entity> entityClass;

    @Autowired
    protected MongoOperations mongoOperations;

    public BaseDao(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public void insertOrUpdate(Entity entity) {
        mongoOperations.save(entity);
    }

    public Optional<AChat> get(long id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), entityClass);
    }

    public List<Entity> getAll() {
        return mongoOperations.findAll(entityClass);
    }

    public boolean remove(long id) {
        WriteResult result = mongoOperations.remove(Query.query(Criteria.where("id").is(id)), entityClass);
        return result.getN() > 0;
    }
}
