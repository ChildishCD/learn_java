package com.javasm.buy.vo;

import com.javasm.goods.model.GoodsModel;

public class GoodsCarVO {
    //商品数量
    private int num;
    private GoodsModel goods;

    public int getNum() {
        return num;
    }

    public void addNum(int n){
        this.num = num + n;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public GoodsModel getGoods() {
        return goods;
    }

    public void setGoods(GoodsModel goods) {
        this.goods = goods;
    }
}
