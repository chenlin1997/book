package com.cl.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(is);
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null，说明 获取连接失败。有值就是获取成功
     */
    public static Connection getConnection(){
        Connection conn=null;

        try {
            conn = dataSource.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 关闭连接，放回数据库连接池
     */
    public static void close(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
