package com.javasm.dao;

import com.javasm.bean.Goods;

import java.util.List;

/**
 * @author: Lisa
 * @className: GoodDao
 * @description:
 * @date: 2021/6/15 11:08
 * @version: 0.1
 * @since: 1.8
 */
public interface GoodsDao extends BaseDao<Goods> {
    int addGood(Goods goods) throws Exception;

    int updateGoodById(Goods goods) throws Exception;
}
