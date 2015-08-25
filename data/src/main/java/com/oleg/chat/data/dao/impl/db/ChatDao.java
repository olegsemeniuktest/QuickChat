package com.oleg.chat.data.dao.impl.db;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.impl.Chat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.MONGO_DB)
public class ChatDao extends BaseDao<Chat> implements IChatDao{

    public ChatDao() {
        super(Chat.class);
    }
}