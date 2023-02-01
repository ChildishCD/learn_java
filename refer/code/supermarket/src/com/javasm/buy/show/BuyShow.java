package com.javasm.buy.show;

import com.javasm.App;
import com.javasm.buy.service.BuyService;
import com.javasm.buy.vo.GoodsCarVO;
import com.javasm.goods.model.GoodsModel;
import com.javasm.goods.service.GoodsService;
import com.javasm.goods.show.GoodsShow;
import com.javasm.member.model.SmMemberModel;


import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class BuyShow {

    private final static GoodsService goodsService = new GoodsService();
    private final static BuyService buyService = new BuyService();
    private static Map<Integer, GoodsCarVO> buyCar;

    public static void start() {
        buyCar = new ConcurrentHashMap<>();

        String goon;
        do {
            System.out.println("\t 1: 添加商品");
            System.out.println("\t 2: 修改商品数量");
            System.out.println("\t 3: 删除购物车中的商品 ");
            System.out.println("\t 4: 展示购物车商品列表");
            System.out.println("\t 请选择菜单");
            int num = App.scanner.nextInt();
            switch (num) {
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    del();
                    break;
                case 4:
                    viewBuyCar();
                    break;
                default:
                    System.out.println("菜单输入错误");
                    break;
            }
            System.out.println("是否返回主菜单？y/n");
            goon = App.scanner.next();
        } while (goon.equals("n"));

    }

    private static void viewBuyCar() {
        System.out.println("======================");
        if (buyCar.isEmpty()) {
            System.out.println("购物车没有商品");
            return;
        }
        //显示购物车列表
        System.out.println("\t|商品ID|商品名称|商品类型|价格|折扣|购买数量|");
        buyCar.forEach((k, v) -> {
            GoodsModel goods = v.getGoods();
            System.out.printf("\t|%d|%s|%s|%s|%d|%d\n",
                    goods.getId(),
                    goods.getGoodsName(),
                    goods.getTypeName(),
                    goods.getPriceStr(),
                    goods.getDiscount(),
                    v.getNum()
            );
        });
        System.out.println("总价："+buyService.totalPrice(buyCar));
        System.out.println("======================");
    }

    private static void del() {
        //删除购物车中商品
        String goon = "y";
        while (goon.equals("y")){
            viewBuyCar();
            System.out.println("请输入要删除的商品id");
            Integer id = App.scanner.nextInt();
            buyCar.remove(id);
            System.out.println("删除成功,是否继续删除？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }

    //修改商品数量
    private static void edit() {
        //显示购物车列表，修改购物车
        String goon = "y";
        while (goon.equals("y")){
            viewBuyCar();
            System.out.println("请输入要修改的商品id");
            Integer id = App.scanner.nextInt();
            System.out.println("请输入要修改的商品数量");
            Integer num = App.scanner.nextInt();
            GoodsCarVO carVO = buyCar.get(id);
            carVO.setNum(num);
            System.out.println("修改成功,是否继续修改？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }


    }

    private static void add() {
        //添加到购物车
        String goon;
        do {
            viewAllGoods();
            System.out.println("输入商品id");
            Integer id = App.scanner.nextInt();
            //显示输入的商品id
            if (viewOneGoods(id) != null){
                System.out.println("输入购买数量");
                Integer num = App.scanner.nextInt();
                //查看购物车内 之前有没有添加过类似的商品，如果添加过，就在原来的基础上继续增加数量
                buyCar.compute(id, (k, v) -> {
                    if (v == null) {//说明之前没有这个商品，重新获取
                        v = buyService.queryByGid(k);
                    }
                    v.addNum(num);
                    return v;
                });
                //显示购物车
                viewBuyCar();
            }

            System.out.println("是否继续添加？y/n");
            goon = App.scanner.next();
        } while ("y".equals(goon));
        //不继续添加了
        // 问问是否结账
        System.out.println("是否确认购买？y/n");
        String isbuy = App.scanner.next();
        if ("y".equals(isbuy)) {
            while (true) {
                //说明购买
                System.out.println("是否使用会员卡购买？y/n");
                String isMember = App.scanner.next();
                if ("y".equals(isMember)) {
                    System.out.println("请输入会员卡号");
                    String name = App.scanner.next();
                    System.out.println("请输入会员卡密码");
                    String pass = App.scanner.next();
                    SmMemberModel memberModel = buyService.checkMember(name, pass);
                    if (memberModel != null) {//有这个会员
                        if (buyService.memberBuy(memberModel, buyCar)){
                            System.out.println("购买成功");
                            //清空购物车//如果使用clear 会影响多线程中的数据
                            buyCar = new ConcurrentHashMap<>();
                            break;
                        }else {
                            System.out.println("余额不足，请更换支付方式");
                        }
                    } else {
                        System.out.println("卡号/密码错误，请重新输入");
                    }
                } else {
                    //现金结账
                    if (buyService.cashBuy(buyCar)){
                        System.out.println("购买成功");
                        buyCar = new ConcurrentHashMap<>();
                        break;
                    }else {
                        System.out.println("系统故障");
                    }
                }
            }
        } else {
            //不购买就清空购物车，下一位
            System.out.println("欢迎下次光临");

        }

    }

    private static GoodsModel viewOneGoods(Integer id) {
        GoodsModel goods = goodsService.queryById(id);
        if (goods !=null){
            System.out.println("\t|商品ID|商品名称|商品类型|价格|库存|状态|折扣|");
            System.out.printf("\t|%d|%s|%s|%s|%s|%s|%d|\n",
                    goods.getId(),
                    goods.getGoodsName(),
                    goods.getTypeName(),
                    goods.getPriceStr(),
                    goods.getInventory(),
                    goods.getStateStr(),
                    goods.getDiscount()
            );
        }else {
            System.out.println("查无此商品");
        }
        return goods;

    }

    //展示所以的商品列表
    private static void viewAllGoods() {
        System.out.println("##########################");
        GoodsShow.viewGoodsList();
        System.out.println("##########################");
    }
}
