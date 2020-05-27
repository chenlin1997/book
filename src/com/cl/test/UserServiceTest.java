package com.cl.test;

import com.cl.pojo.User;
import com.cl.service.UserService;
import com.cl.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"dachong","123456","dachong@163.com"));
        userService.registUser(new User(null,"chenlin","177852","chenlin@163.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"dachong","123456",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("dachong")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}