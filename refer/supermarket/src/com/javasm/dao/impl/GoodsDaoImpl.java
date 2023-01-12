package com.javasm.dao.impl;

import com.javasm.bean.Goods;
import com.javasm.dao.GoodsDao;
import com.javasm.sql.GoodsSql;
import com.javasm.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;

/**
 * @author: Lisa
 * @className: GoodsDaoImpl
 * @description:
 * @date: 2021/6/15 11:10
 * @version: 0.1
 * @since: 1.8
 */
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {
    public GoodsDaoImpl() {
        super(Goods.class);
    }

    private Connection connection;

    public GoodsDaoImpl(Connection connection) {
        super(Goods.class);
        this.connection = connection;
    }

    @Override
    public int addGood(Goods goods) throws Exception {
        return new QueryRunner(DBUtil.getDataSource()).update(
                GoodsSql.ADD_GOOD,
                goods.getGoodName(),
                goods.getTypeId(),
                goods.getStore(),
                goods.getPrice());
    }

    @Override
    public int updateGoodById(Goods goods) throws Exception {
        if (connection == null) {
            return new QueryRunner(DBUtil.getDataSource()).update(
                    GoodsSql.UPDATE_GOOD_BY_ID,
                    goods.getGoodName(),
                    goods.getTypeId(),
                    goods.getStore(),
                    goods.getGoodStatus(),
                    goods.getDiscount(),
                    goods.getPrice(),
                    goods.getId());
        }
        return new QueryRunner().update(connection,
                GoodsSql.UPDATE_GOOD_BY_ID,
                goods.getGoodName(),
                goods.getTypeId(),
                goods.getStore(),
                goods.getGoodStatus(),
                goods.getDiscount(),
                goods.getPrice(),
                goods.getId());
    }
}
