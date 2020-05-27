package com.cl.web;

import com.cl.pojo.User;
import com.cl.service.UserService;
import com.cl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));
        //2.调用userService.login()登录处理业务
        if (loginUser==null){
            //等于null,登录失败,跳回登录页面
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            //把错误信息，和回显的表单项信息，保存到request域中

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //登录成功，跳到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
