package com.oleg.chat.data.services.impl.db;

import com.oleg.chat.data.dao.SequenceDao;
import com.oleg.chat.data.entities.AEntity;

import javax.annotation.Resource;

/**
 * Created by oleg on 09.03.2015.
 */
public abstract class AService<Entity extends AEntity> {

    @Resource
    private SequenceDao sequenceDao;

    public void insert(Entity entity) {
        entity.setId(sequenceDao.getNextSequenceId(entity.getCollectionName()));
        saveOrUpdate(entity);
    }

    public abstract void saveOrUpdate(Entity entity);

}
