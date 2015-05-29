package com.oleg.chat.data.services.impl;

import com.oleg.chat.data.beans.Authority;
import com.oleg.chat.data.dao.ChatDao;
import com.oleg.chat.data.dao.UserDao;
import com.oleg.chat.data.domain.Chat;
import com.oleg.chat.data.domain.User;
import com.oleg.chat.data.services.IChatService;
import com.oleg.chat.data.services.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by oleg on 14.03.2015.
 */
@Service
public class UserService extends AService<User> implements IUserService, UserDetailsService {

    @Resource
    private UserDao userDao;


    @Override
    public void saveOrUpdate(User entity) {
        userDao.saveOrUpdate(entity);
    }

    @Override
    public void remove(long id) {
        userDao.remove(id);
    }

    @Override
    public User getByNickName(String nickname) {
        return userDao.findByNickname(nickname);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setNickname("test");
        user.addAuthority(Authority.USER);
//        return userDao.findByNickname(username);
        return user;
    }
}
