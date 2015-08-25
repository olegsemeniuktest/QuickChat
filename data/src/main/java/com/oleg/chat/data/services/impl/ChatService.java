package com.oleg.chat.data.services.impl;

import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.impl.Chat;
import com.oleg.chat.data.entities.impl.User;
import com.oleg.chat.data.services.IChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
@Service
public class ChatService extends AService<Chat> implements IChatService<Chat> {

    @Resource
    private IChatDao chatDao;

    @Override
    public void insertOrUpdate(Chat entity) {
        chatDao.insertOrUpdate(entity);
    }

    @Override
    public void remove(long id) {
        chatDao.remove(id);
    }

    @Override
    public Chat get(long id) {
        return chatDao.get(id);
    }

    public Collection<Chat> getAll() {
        return chatDao.getAll();
    }

    @Override
    public List<User> getActiveUsers(long chatId) {
        Chat chat = chatDao.get(chatId);
        return chat.getActiveUsers();
    }

    @Override
    public boolean isUserHasAccess(long chatId, User user) {
        return false;
    }
}
