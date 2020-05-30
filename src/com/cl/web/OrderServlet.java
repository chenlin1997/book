package com.cl.web;

import com.cl.dao.impl.OrderItemDaoImpl;
import com.cl.pojo.Cart;
import com.cl.pojo.User;
import com.cl.service.OrderService;
import com.cl.service.impl.OrderServiceImpl;
import com.cl.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService= new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取 Userid
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        // 调用 orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);

        // req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
