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
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page（pageNo,pageSize）;Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);

        //3.跳转到图书列表页面，这里不能使用请求转发，因为当用户提交完请求，浏览器会记录下最后一次请求的全部信息。
        // 当用户按下功能键 F5，就会发起浏览器记录的最后一次请求。
        // /manager/bookServlet?action=list
// req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        //请求转发的'/'表示到工程名，重定向的'/'表示到端口号
        //这里使用重定向
        //System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数id,图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        //3.重定向回图书列表的管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用BookService.updateBook(book);修改图书
        bookService.updateBook(book);
        //3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3.保存图书到Request域中
        req.setAttribute("book",book);
        //4.请求转发到 pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过 BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到 Request 域中
        req.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
