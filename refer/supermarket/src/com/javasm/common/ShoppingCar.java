package com.javasm.common;

import com.javasm.bean.Goods;
import com.javasm.vo.GoodVO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Lisa
 * @className: ShoopingCar
 * @description:
 * @date: 2021/6/15 15:36
 * @version: 0.1
 * @since: 1.8
 */
public class ShoppingCar {

    //创建购物车
    public static Map<Integer, GoodVO> car;

    static {
        car = new HashMap<>(16);
    }

    public static void put(int buyNum, Goods buyGood) {
        //判断之前有没有买过
        Integer goodId = buyGood.getId();
        GoodVO goodVO = car.get(goodId);
        if (goodVO == null) {
            car.put(goodId, GoodVO.builder().goods(buyGood).buyNum(buyNum).build());
        } else {
            goodVO.setBuyNum(goodVO.getBuyNum() + buyNum);
        }

    }

    public static boolean select() {
        if (car.isEmpty()) {
            System.out.println("购物车目前没有商品，无法操作");
            return false;
        }
        car.forEach((gid, good) -> {
            System.out.println(gid + ":" + good);
        });
        return true;
    }

    public static BigDecimal getTotalMoney() {
        BigDecimal totalMoney = new BigDecimal("0");
        Set<Map.Entry<Integer, GoodVO>> entrySet = car.entrySet();
        for (Map.Entry<Integer, GoodVO> entry : entrySet) {
            totalMoney = totalMoney.add(entry.getValue().getMoney());
        }
        return totalMoney;
    }
}
