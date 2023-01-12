package com.javasm.service.impl;

import com.javasm.bean.Goods;
import com.javasm.common.CodeEnum;
import com.javasm.common.ServerResponse;
import com.javasm.dao.GoodsDao;
import com.javasm.dao.impl.GoodsDaoImpl;
import com.javasm.service.GoodsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Lisa
 * @className: GoodsServiceImpl
 * @description:
 * @date: 2021/6/15 14:42
 * @version: 0.1
 * @since: 1.8
 */
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public ServerResponse addGoods(Goods goods) {
        try {
            if (goodsDao.addGood(goods) == 1) {
                return ServerResponse.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }

    @Override
    public ServerResponse deleteGoodById(int id) {
        try {
            if (goodsDao.deleteOne(id) == 1) {
                return ServerResponse.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }

    @Override
    public ServerResponse<Goods> selectGoodsById(int id) {
        try {
            Goods goods = goodsDao.selectOne(id);
            if (goods != null) {
                return ServerResponse.success(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }

    @Override
    public ServerResponse updateGoodById(Goods goods) {

        try {
            if (goodsDao.updateGoodById(goods) == 1) {
                return ServerResponse.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }

    static Map<String, Object> params;

    static {
        params = new HashMap<>(16);
    }

    @Override
    public ServerResponse<List<Goods>> selectAllGood() {

        try {
            params.clear();
            params.put("good_status", CodeEnum.GOOD_ON_SALE.getCode());
            List<Goods> goodsList = goodsDao.selectByPrams(params);
            if (!goodsList.isEmpty()) {
                return ServerResponse.success(goodsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }
}
