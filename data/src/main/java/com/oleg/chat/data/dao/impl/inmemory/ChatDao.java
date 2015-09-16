package com.oleg.chat.data.dao.impl.inmemory;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.IN_MEMORY)
public class ChatDao implements IChatDao {

    private final Set<AChat> chatMap = new TreeSet<AChat>(new Comparator<AChat>() {
        @Override
        public int compare(AChat o1, AChat o2) {
            return o1.getCreateTime().compareTo(o2.getCreateTime());
        }
    });

    @Override
    public void insertOrUpdate(AChat entity) {
        chatMap.add(entity);
    }

    @Override
    public Optional<AChat> get(long id) {
        return chatMap.stream().filter(new Predicate<AChat>() {
            @Override
            public boolean test(AChat aChat) {
               return aChat.getId() == id;
            }
        }).findFirst();
    }

    @Override
    public Collection<Chat> getAll() {
        return chatMap.values();
    }

    @Override
    public boolean remove(long id) {
        return chatMap.remove(id) != null;
    }

    @Override
    public Collection<AChat> getPublicChats() {
        return null;
    }

    @Override
    public Collection<AChat> getPublicChatsPart(Date timeFrom, int partSize) {
        return null;
    }

    @Override
    public Collection<AChat> getPrivateChats(long userId) {
        return null;
    }

    @Override
    public Collection<AChat> getPrivateChats(long userId, Date timeFrom, int partSize) {
        return null;
    }

    @Override
    public boolean isUserHasAccess(long chatId, long userId) {
        return false;
    }
}