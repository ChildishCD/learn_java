package com.javasm.dao;

import com.javasm.bean.OrderInfo;

/**
 * @author: Lisa
 * @className: OrderInfoDao
 * @description:
 * @date: 2021/6/15 16:19
 * @version: 0.1
 * @since: 1.8
 */
public interface OrderInfoDao {
    long addOrder(OrderInfo orderInfo) throws Exception;

    void addOrderDetail(int oid,int gid,int buyNum) throws Exception;
}
