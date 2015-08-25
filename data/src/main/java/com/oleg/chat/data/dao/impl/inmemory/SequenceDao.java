package com.oleg.chat.data.dao.impl.inmemory;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.ISequenceDao;
import com.oleg.chat.data.entities.impl.Sequence;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.IN_MEMORY)
public class SequenceDao implements ISequenceDao {

    private final Map<String, Sequence> sequenceMap = new HashMap<>();

    @Override
    public void insert(Sequence sequence) throws DuplicateKeyException {
        if (sequenceMap.containsKey(sequence.getId())) {
            throw new DuplicateKeyException("Duplicate key of sequence");
        }
        sequenceMap.put(sequence.getId(), sequence);
    }

    @Override
    public Sequence get(String key) {
        return sequenceMap.get(key);
    }

    @Override
    public Long getNextSequenceId(String key) {
        Sequence seq = sequenceMap.get(key);
        Long next = seq.getSequence() + 1;
        seq.setSequence(next);
        return next;
    }
}
