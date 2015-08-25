package com.oleg.chat.data.dao.impl.db;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.ISequenceDao;
import com.oleg.chat.data.entities.impl.Sequence;
import com.oleg.chat.data.utils.exceptions.RuntimeDBException;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.MONGO_DB)
public class SequenceDao implements ISequenceDao {

    @Resource
    private MongoOperations mongoOperations;

    public void insert(Sequence sequence) throws DuplicateKeyException {
        mongoOperations.insert(sequence);
    }

    public Sequence get(String key) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(key)), Sequence.class);
    }

    public Long getNextSequenceId(String key) {
        Query query = new Query(Criteria.where("id").is(key));

        Update update = new Update();
        update.inc("sequence", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class);
        if (sequence == null)
            throw new RuntimeDBException("Unable to get sequence for key: " + key);

        return sequence.getSequence();
    }


}
