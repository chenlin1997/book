package com.cl.test;

import com.cl.dao.BookDao;
import com.cl.dao.impl.BookDaoImpl;
import com.cl.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"微积分上", "陈林", new BigDecimal(99),38,1,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"微积分", "陈林", new BigDecimal(99),99,10,null));

    }

    @Test
    public void queryBookById() {
        System.out.println( bookDao.queryBookById(21) );
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}