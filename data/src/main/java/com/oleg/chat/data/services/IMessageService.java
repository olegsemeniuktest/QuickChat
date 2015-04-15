package com.oleg.chat.data.services;

import com.oleg.chat.data.domain.Message;

import java.util.Date;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
public interface IMessageService {

    void insert(Message entity);

    void saveOrUpdate(Message entity);

    void remove(long id);

    List<Message> getMessagesByChat(long chatId);

    List<Message> getMessagesByChat(long chatId, int limit);

    List<Message> getMessagesByChat(long chatId, Date afterTime);
}
