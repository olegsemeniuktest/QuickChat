package com.oleg.chat.data.dao.impl.db;

import com.oleg.chat.data.config.DataProfiles;
import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.chats.AChat;
import com.oleg.chat.data.entities.impl.chats.Chat;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
@Profile(DataProfiles.MONGO_DB)
public class ChatDao extends BaseDao<AChat> implements IChatDao {

    public ChatDao() {
        super(AChat.class);
    }

    @Override
    public Collection<AChat> getPublicChats() {
        Query query = Query.query(Criteria.where("isPrivate").not());
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return mongoOperations.find(query, AChat.class);
    }

    @Override
    public Collection<AChat> getPublicChatsPart(Date timeFrom, int partSize) {
        Query query = Query.query(Criteria.where("isPrivate").not().and("createTime").gt(timeFrom)).limit(partSize);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return mongoOperations.find(query, AChat.class);
    }

    @Override
    public Collection<AChat> getPrivateChats(long userId) {
        Query query = Query.query(Criteria.where("isPrivate").and("members").is(userId));
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return mongoOperations.find(query, AChat.class);
    }

    @Override
    public Collection<AChat> getPrivateChats(long userId, Date timeFrom, int partSize) {
        Query query = Query.query(Criteria.where("isPrivate").and("members").is(userId).and("createTime").gt(timeFrom)).limit(partSize);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return mongoOperations.find(query, AChat.class);
    }

    @Override
    public boolean isUserHasAccess(long chatId, long userId) {
        return mongoOperations.exists(Query.query(Criteria.where("id").is(chatId).andOperator(Criteria.where("isPrivate").not().orOperator(Criteria.where("members").is(userId)))), AChat.class);
    }
}