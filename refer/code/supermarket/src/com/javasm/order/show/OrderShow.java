package com.javasm.order.show;

import com.javasm.App;
import com.javasm.goods.model.GoodsModel;
import com.javasm.goods.service.GoodsService;
import com.javasm.order.model.SmOrderModel;
import com.javasm.order.service.OrderService;
import com.javasm.utils.DateUtilJavasm;

import java.util.List;

public class OrderShow {

    private static OrderService orderService = new OrderService();
    private static GoodsService goodsService = new GoodsService();

    public static void start() {
        System.out.println("订单查询");
        String goon;
        do {
            System.out.println("请输入要商品编号或会员编号");
            Integer id = App.scanner.nextInt();
            List<SmOrderModel> list = orderService.queryListById(id);
            if (list == null) {
                System.out.println("查无此数据");
            } else {
                System.out.println("\t|订单ID|会员ID|消费金额（元）|创建时间|支付方式|");
                list.forEach(order -> {
                    System.out.printf("\t|%d|%d|%s|%s|%s|\n",
                            order.getId(),
                            order.getMemberId(),
                            order.getTotalPrice(),
                            DateUtilJavasm.timeToStringAll(order.getCreateTime()),
                            order.getPayTypeStr()
                    );
                    if (order.getOrderInfoList() != null) {
                        //输出订单详情
                        System.out.println("\t\t------------------------------------");
                        System.out.println("\t\t\t|商品ID|商品名称|商品数量|");
                        order.getOrderInfoList().forEach(info -> {
                            //这里并没有去数据库查询，所以不是循环操作数据库，速度很快
                            GoodsModel goods = goodsService.queryById(info.getGoodsId());
                            System.out.printf("\t\t\t|%d|%s|%d|\n",
                                    info.getGoodsId(),
                                    goods.getGoodsName(),
                                    info.getGoodsNum()
                            );
                        });
                        System.out.println("\t\t------------------------------------");
                    }
                });
            }
            System.out.println("是否继承查询？y/n");
            goon = App.scanner.next();
        }while ("y".equals(goon));

    }
}
