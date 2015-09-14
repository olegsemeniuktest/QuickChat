package com.oleg.chat.data.dao.impl.inmemory;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.impl.chats.Chat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.IN_MEMORY)
public class ChatDao implements IChatDao {

    private final Map<Long, Chat> chatMap = new HashMap<>();

    @Override
    public void insertOrUpdate(Chat entity) {
        chatMap.put(entity.getId(), entity);
    }

    @Override
    public Chat get(long id) {
        return chatMap.get(id);
    }

    @Override
    public Collection<Chat> getAll() {
        return chatMap.values();
    }

    @Override
    public boolean remove(long id) {
        return chatMap.remove(id) != null;
    }
}