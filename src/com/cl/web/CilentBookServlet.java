package com.cl.web;

import com.cl.pojo.Book;
import com.cl.pojo.Page;
import com.cl.service.BookService;
import com.cl.service.impl.BookServiceImpl;
import com.cl.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CilentBookServlet  extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("经过了前台的ClientBookServlet程序");
        //1.获取请求的参数 pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page（pageNo,pageSize）;Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/shouye.jsp").forward(req,resp);
    }
}
