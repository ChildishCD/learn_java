package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.MemberOrdersProductsModel;

import java.util.List;

public class OrdersProductsDAO extends BaseDAO<MemberOrdersProductsModel> {
    public void insertOrderProducts(List<MemberOrdersProductsModel> orderProducts){
        super.saveListWithoutId(orderProducts);
    }

    public List<MemberOrdersProductsModel> selectOrdersProductsByOId(Integer oId) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE order_id = ?";
        return selectList(sql, oId);
    }

    public List<MemberOrdersProductsModel> selectOrdersProductsByPId(Integer pId) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE product_id = ?";
        return selectList(sql, pId);
    }

}
