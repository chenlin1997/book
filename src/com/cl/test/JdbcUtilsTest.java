package com.cl.test;

import com.cl.utils.JdbcUtils;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i=1;i<100;i++){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
//            JdbcUtils.close(connection);
        }
    }
}
