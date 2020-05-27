package com.cl.test;

import com.cl.dao.UserDao;
import com.cl.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("密码正确");
        }
    }

    @Test
    public void saveUser() {
    }
}