package com.cl.web;

import com.cl.pojo.User;
import com.cl.service.UserService;
import com.cl.service.impl.UserServiceImpl;
import com.cl.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成map对象
        Map<String,Object>resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        //2.重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            //登录成功，
            //保存用户登录的信息到session域中
            req.getSession().setAttribute("user",loginUser);
            // 跳到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求的参数
        String username = req.getParameter("username");//用户名
        String password = req.getParameter("password");//密码
        String email = req.getParameter("email");//邮箱
        String code = req.getParameter("code");//验证码


        User user =  WebUtils.copyParamToBean(req.getParameterMap(),new User());


        //2.检查 验证码是否正确====写死，要求验证码为：abcd
        if (token!=null&&token.equalsIgnoreCase(code)){
            //验证码正确
            //3.检查 用户名是否可用
            if (userService.existsUsername(username)){
                //返回true，用户名已存在，不可用
                System.out.println("用户名【"+username+"】已存在！");
                //把回显信息，保存到Request域中
                req.setAttribute("msg","用户名已存在！！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                //跳到注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //返回false,用户名不存在，可用
                userService.registUser(new User(null,username,password,email));
                req.getSession().setAttribute("user",user);
                //跳到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //验证码不正确，跳转回注册页面

            //把回显信息，保存到Request域中
            req.setAttribute("msg","验证码错误！！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            System.out.println("验证码【"+code+"】错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

}
