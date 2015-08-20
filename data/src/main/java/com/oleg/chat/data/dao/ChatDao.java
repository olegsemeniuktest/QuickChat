package com.oleg.chat.data.dao;

import com.oleg.chat.data.entities.impl.Chat;
import org.springframework.stereotype.Repository;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
public class ChatDao extends BaseDao<Chat> {

    public ChatDao() {
        super(Chat.class);
    }
}