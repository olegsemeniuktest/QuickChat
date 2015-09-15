package com.oleg.chat.data.services.impl;

import com.oleg.chat.data.dao.IUserDao;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.User;
import com.oleg.chat.data.services.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by oleg on 14.03.2015.
 */
@Service
public class UserService extends AService<User> implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public void insertOrUpdate(User entity) {
        userDao.insertOrUpdate(entity);
    }

    @Override
    public void remove(long id) {
        userDao.remove(id);
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(userDao.get(id));
    }

    @Override
    public Optional<User> getByNickName(String nickname) {
        return Optional.ofNullable(userDao.findByNickname(nickname));
    }

    public boolean isNicknameUsed(String nickname) {
        return userDao.existNickname(nickname);
    }

    @Override
    public void expireUser(IUser user) {

    }
}
