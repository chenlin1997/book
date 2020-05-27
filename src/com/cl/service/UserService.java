package com.cl.service;

import com.cl.pojo.User;

public interface UserService {
    /**
     * 注册用户的业务
     * @param user
     */
    void registUser(User user);

    /**
     * 登录的业务
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，登录成功
     */
    User login(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回ture表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
