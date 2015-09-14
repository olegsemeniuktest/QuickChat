package com.oleg.chat.data.dao.impl.db;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.MONGO_DB)
public class ChatDao extends BaseDao<AChat> implements IChatDao{

    public ChatDao() {
        super(AChat.class);
    }

    @Override
    public Collection<IChat> getAllPublic() {
        return null;
    }

    @Override
    public Collection<IChat> getAllPrivate(IUser user) {
        return null;
    }
}