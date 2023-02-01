package com.javasm.order.dao;

import com.javasm.base.BaseDAO;
import com.javasm.order.model.SmOrderInfoModel;
import com.javasm.utils.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class OrderInfoDAO extends BaseDAO<SmOrderInfoModel> {

    public void insertOrderInfoByList(List<SmOrderInfoModel> list){
        String sql = "INSERT INTO sm_order_info (order_id,goods_id,goods_num) VALUES (?,?,?)";
        Object[][] objects = new Object[list.size()][3];
        for (int i=0;i<list.size();i++){
            SmOrderInfoModel info = list.get(i);
            objects[i] = new Object[]{info.getOrderId(),info.getGoodsId(),info.getGoodsNum()};
        }
        try {
            DBHelper.getQueryRunner().batch(sql,objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SmOrderInfoModel> selectListByOrderId(Integer orderId){
        String sql = "SELECT * FROM sm_order_info WHERE order_id = ?";
        return selectList(sql,orderId);
    }

    public List<SmOrderInfoModel> selectListByGoodsId(Integer goodsId){
        String sql = "SELECT * FROM sm_order_info WHERE goods_id = ?";
        return selectList(sql,goodsId);
    }
}
