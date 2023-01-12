package com.javasm.vo;

import com.javasm.bean.Goods;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Lisa
 * @className: GoodVO
 * @description:
 * @date: 2021/6/15 15:33
 * @version: 0.1
 * @since: 1.8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodVO implements Serializable {
    private static final long serialVersionUID = 8270537976542897114L;

    private Goods goods;
    private Integer buyNum;
    private BigDecimal money;//小计


    public BigDecimal getMoney() {
        return BigDecimal.valueOf(goods.getPrice() * buyNum);
    }

    @Override
    public String toString() {
        return "GoodVO{" +
                "goods=" + goods +
                ", buyNum=" + buyNum +
                ", money=" + getMoney() +
                '}';
    }
}
