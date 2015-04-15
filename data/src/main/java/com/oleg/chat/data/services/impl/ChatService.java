package com.oleg.chat.data.services.impl;

import com.oleg.chat.data.dao.ChatDao;
import com.oleg.chat.data.domain.Chat;
import com.oleg.chat.data.domain.User;
import com.oleg.chat.data.services.IChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
@Service
public class ChatService extends AService<Chat> implements IChatService<Chat> {

    @Resource
    private ChatDao chatDao;

    @Override
    public void saveOrUpdate(Chat entity) {
        chatDao.saveOrUpdate(entity);
    }

    @Override
    public void remove(long id) {
        chatDao.remove(id);
    }

    @Override
    public Chat get(long id) {
        return chatDao.get(id);
    }

    public List<Chat> getAll() {
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
