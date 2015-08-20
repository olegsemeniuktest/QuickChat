package com.oleg.chat.data.dao;

import com.oleg.chat.data.entities.impl.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User findByNickname(String nickname) {
        return mongoOperations.findOne(Query.query(Criteria.where("nickname").is(nickname)), User.class);
    }

    public boolean existNickname(String nickname) {
        return mongoOperations.exists(Query.query(Criteria.where("nickname").is(nickname)), User.class);
    }
}
