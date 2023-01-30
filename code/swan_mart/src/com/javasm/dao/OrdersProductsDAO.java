package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.MemberOrdersProductsModel;

import java.util.List;

public class OrdersProductsDAO extends BaseDAO<MemberOrdersProductsModel> {
    public void insertOrderProducts(List<MemberOrdersProductsModel> orderProducts){
        super.saveListWithoutId(orderProducts);
    }
}
