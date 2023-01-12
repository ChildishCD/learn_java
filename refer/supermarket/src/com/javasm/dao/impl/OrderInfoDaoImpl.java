package com.javasm.dao.impl;

import com.javasm.bean.OrderInfo;
import com.javasm.dao.OrderInfoDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;

/**
 * @author: Lisa
 * @className: OrderInfoDaoImpl
 * @description:
 * @date: 2021/6/15 16:19
 * @version: 0.1
 * @since: 1.8
 */
public class OrderInfoDaoImpl implements OrderInfoDao {
    private Connection connection;

    public OrderInfoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long addOrder(OrderInfo orderInfo) throws Exception {
        String sql = "insert into  orderinfo (mid, total_money, pay_type)  values (?,?,?)";
        return new QueryRunner().insert(connection, sql, new ScalarHandler<>(), orderInfo.getMid(), orderInfo.getTotalMoney(), orderInfo.getPayType());
    }

    @Override
    public void addOrderDetail(int oid, int gid, int buyNum) throws Exception {
        String sql = "insert  into  orderdetail (oid, gid, buy_num) VALUES (?,?,?)";
        new QueryRunner().update(connection, sql, oid, gid, buyNum);
    }
}
