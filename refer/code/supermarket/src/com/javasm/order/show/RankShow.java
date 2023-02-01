package com.javasm.order.show;

import com.javasm.App;
import com.javasm.order.service.OrderService;
import com.javasm.order.vo.RankVO;

import java.util.List;

public class RankShow {

    private static OrderService orderService = new OrderService();
    public static void start() {
        System.out.println("排行榜统计");
        String goon;
        do {
            System.out.println("请输入查询的月份(1~12)");
            Integer month = App.scanner.nextInt();
            System.out.println("请输入查询的商品类型(-1查询全部类型)");
            Integer type = App.scanner.nextInt();
            List<RankVO> rank = orderService.rank(month, type);
            System.out.println("\t|商品ID|商品名称|商品类型|总销量|");
            rank.forEach(rankVO -> {
                System.out.printf("\t|%d|%s|%s|%d|\n",
                        rankVO.getId(),
                        rankVO.getName(),
                        rankVO.getType(),
                        rankVO.getNum()
                );
            });
            System.out.println("是否继续查询？y/n");
            goon = App.scanner.next();
        }while ("y".equals(goon));

    }
}
