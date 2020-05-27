package com.cl.service.impl;

import com.cl.dao.UserDao;
import com.cl.dao.impl.UserDaoImpl;
import com.cl.pojo.User;
import com.cl.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
