package com.oleg.chat.data.services.impl;

import com.oleg.chat.data.dao.ISequenceDao;
import com.oleg.chat.data.entities.AEntity;

import javax.annotation.Resource;

/**
 * Created by oleg on 09.03.2015.
 */
public abstract class AService<Entity extends AEntity> {

    @Resource
    private ISequenceDao sequenceDao;

    public void save(Entity entity) {
        if (entity.getId() == null) {
            entity.setId(sequenceDao.getNextSequenceId(entity.getCollectionName()));
        }
        insertOrUpdate(entity);
    }

    protected abstract void insertOrUpdate(Entity entity);

}
