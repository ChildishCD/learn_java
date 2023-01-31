package com.javasm.vo;

import com.javasm.base.BaseVO;
import com.javasm.bean.MemberOrdersModel;
import com.javasm.bean.ProductsModel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ShoppingVO extends BaseVO<MemberOrdersModel> {
    String name;
    Integer count;
    ProductsModel product;

    public ShoppingVO(Integer count, ProductsModel product) {
        this.count = count;
        this.product = product;
        this.name = product.getName();
    }

    public Double getPrice() {
        return product.getPrice() * product.getDiscount() * count;
    }

    @Override
    public String toString() {
        return " 商品id: " + product.getId() +
                " 商品名称: " + this.name +
                " 商品数量: " + this.count;
    }

    public static Map<Integer, ShoppingVO> shotCar(){
        return new HashMap<>();
    }
}
