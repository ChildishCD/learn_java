package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.MemberOrdersModel;

public class OrdersDAO extends BaseDAO<MemberOrdersModel> {
    public int insertOrder(MemberOrdersModel order){
        super.saveOrUpdate(order);
        int oId;
        String sql = "SELECT * FROM " + getTableName() +
                " WHERE member_id = ?" +
                " ORDER BY create_time DESC LIMIT 1";
        return super.selectObject(sql,order.getMemberId()).getId();
    }
}
