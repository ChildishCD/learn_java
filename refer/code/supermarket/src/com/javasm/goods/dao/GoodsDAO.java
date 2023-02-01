package com.javasm.goods.dao;

import com.javasm.base.BaseDAO;
import com.javasm.goods.model.GoodsModel;

import java.util.List;

public class GoodsDAO extends BaseDAO<GoodsModel> {

    public List<GoodsModel> selectGoodsList(){
        String sql = "SELECT goods.*,type.type_name FROM sm_goods goods,sm_goods_type type \n" +
                "WHERE goods.goods_type = type.id AND goods.is_del = 0";
        return selectList(sql);
    }
}
