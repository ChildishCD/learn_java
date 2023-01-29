package com.javasm.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Shopping{
    String name;
    Integer count;
    ProductsModel product;

    public Shopping(Integer count, ProductsModel product) {
        this.count = count;
        this.product = product;
        this.name = product.getName();
    }

    @Override
    public String toString() {
        return " 商品id: "+ product.getId() +
                " 商品名称: "+ this.name +
                " 商品数量: "+ this.count;
    }
}
