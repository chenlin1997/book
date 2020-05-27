package com.cl.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");//防止乱码的代码，参考网上
        String action = req.getParameter("action");

//        if ("login".equals(action)) {
//            login(req, resp);
//        } else if ("regist".equals(action)) {
//            regist(req, resp);
//        }
        try {
            //获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //调用目标业务 方法
            method.invoke(this, req, resp);
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }
}
