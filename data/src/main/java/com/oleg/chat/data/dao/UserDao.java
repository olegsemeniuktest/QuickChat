package com.oleg.chat.data.dao;

import com.oleg.chat.data.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by oleg on 08.03.2015.
 */
@Repository
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }
}
