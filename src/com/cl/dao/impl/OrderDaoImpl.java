package com.cl.dao.impl;

import com.cl.dao.OrderDao;
import com.cl.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
