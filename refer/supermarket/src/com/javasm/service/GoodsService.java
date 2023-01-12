package com.javasm.service;

import com.javasm.bean.Goods;
import com.javasm.common.ServerResponse;

import java.util.List;

/**
 * @author: Lisa
 * @className: GoodsService
 * @description:
 * @date: 2021/6/15 14:41
 * @version: 0.1
 * @since: 1.8
 */
public interface GoodsService {
    ServerResponse addGoods(Goods goods);

    ServerResponse deleteGoodById(int id);

    ServerResponse<Goods> selectGoodsById(int id);

    ServerResponse updateGoodById(Goods goods);

    ServerResponse<List<Goods>> selectAllGood();
}
