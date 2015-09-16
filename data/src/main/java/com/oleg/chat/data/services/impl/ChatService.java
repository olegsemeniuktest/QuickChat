package com.oleg.chat.data.services.impl;

import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;
import com.oleg.chat.data.services.IChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 14.03.2015.
 */
@Service
public class ChatService extends AService<AChat> implements IChatService {

    @Resource
    private IChatDao chatDao;

    @Override
    public void insertOrUpdate(AChat entity) {
        chatDao.insertOrUpdate(entity);
    }

    @Override
    public void save(Chat entity) {

    }

    @Override
    public void remove(long id) {
        chatDao.remove(id);
    }

    @Override
    public IChat get(long id) {
        return chatDao.get(id);
    }

    @Override
    public Collection<AChat> getPublicChats() {
        return chatDao.getPublicChats();
    }

    @Override
    public Collection<AChat> getPublicChatsPart(Date timeFrom, int partSize) {
        return chatDao.getPublicChatsPart(timeFrom, partSize);
    }

    @Override
    public Collection<AChat> getPrivateChats(IUser user) {
        return chatDao.getPrivateChats(user.getId());
    }

    @Override
    public Collection<AChat> getPrivateChats(IUser user, Date timeFrom, int partSize) {
        return chatDao.getPrivateChats(user.getId(), timeFrom, partSize);
    }


    @Override
    public Collection<IUser> getActiveUsers(long chatId) {
        IChat chat = chatDao.get(chatId);
        return chat.getActiveUsers();
    }

    @Override
    public boolean isUserHasAccess(long chatId, IUser user) {
        return chatDao.isUserHasAccess(chatId, user.getId());
    }


}
