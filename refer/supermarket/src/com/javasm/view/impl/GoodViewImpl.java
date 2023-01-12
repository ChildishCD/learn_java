package com.javasm.view.impl;

import com.javasm.bean.Goods;
import com.javasm.common.CodeEnum;
import com.javasm.service.GoodsService;
import com.javasm.service.impl.GoodsServiceImpl;
import com.javasm.util.InputUtil;
import com.javasm.view.GoodView;

/**
 * @author: Lisa
 * @className: GoodViewImpl
 * @description:
 * @date: 2021/6/15 10:10
 * @version: 0.1
 * @since: 1.8
 */
public class GoodViewImpl implements GoodView {

    private static GoodsService goodsService;

    static {
        goodsService = new GoodsServiceImpl();
    }

    @Override
    public void menu() {
        while (true) {
            System.out.println("1.添加商品");
            System.out.println("2.修改商品");
            System.out.println("3.删除商品");
            System.out.println("4.查询商品");
            System.out.println("5.退出");

            int choice = InputUtil.inputInt("^[1-5]{1}$", "请录入1-5的数字:");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    select();
                    break;
                case 5:
                    System.out.println("退出商品系统");
                    return;
            }
        }
    }

    @Override
    public void add() {

        System.out.println("录入商品名称:");
        String name = InputUtil.input.next();
        new TypeViewImpl().selectChild();
        System.out.println("录入商品所属类型:");
        int typeId = InputUtil.inputInt();
        System.out.println("录入商品单价:");
        float price = InputUtil.input.nextFloat();
        System.out.println("录入商品库存:");
        int store = InputUtil.inputInt();
        //Goods goods = new Goods().setGoodName(name).setTypeId(typeId).setPrice(price).setStore(store);
        Goods goods = Goods.builder().goodName(name).typeId(typeId).price(price).store(store).build();
        System.out.println(goodsService.addGoods(goods));

    }

    @Override
    public void delete() {
        System.out.println("录入要删除的商品的id:");
        Goods goods = goodsService.selectGoodsById(InputUtil.inputInt()).getData();
        goods.setGoodStatus(CodeEnum.GOOD_DELETE.getCode());
        System.out.println(goodsService.updateGoodById(goods));
        // System.out.println(goodsService.deleteGoodById(InputUtil.inputInt()));
    }


    @Override
    public void update() {

        System.out.println("录入要修改的商品的id:");
        Goods data = goodsService.selectGoodsById(InputUtil.inputInt()).getData();

        System.out.println("data:" + data);
        System.out.println("1.选择要修改的字段: 1.good_name, 2.type_id, 3.store, 4.good_status, 5.discount, 6.price ");
        String choice = InputUtil.input.next();
        String[] array = choice.split(",");
        for (String s : array) {
            int num = Integer.parseInt(s);
            switch (num) {
                case 1:
                    System.out.println("请录入新的good_name的数据:");
                    data.setGoodName(InputUtil.input.next());
                    break;
                case 2:
                    System.out.println("请录入新的type_id的数据:");
                    new TypeViewImpl().selectChild();
                    data.setTypeId(InputUtil.inputInt());
                    break;
                case 3:
                    System.out.println("请录入新的store的数据:");
                    data.setStore(InputUtil.inputInt());
                    break;
                case 4:
                    System.out.println("请录入新的good_status的数据:");
                    System.out.println(CodeEnum.GOOD_ON_SALE.getCode());
                    System.out.println(CodeEnum.GOOD_OFF_SALE.getCode());
                    System.out.println(CodeEnum.GOOD_DELETE.getCode());
                    data.setGoodStatus(InputUtil.inputInt());
                    break;
                case 5:
                    System.out.println("请录入新的discount的数据:");
                    data.setDiscount(InputUtil.input.nextFloat());
                    break;
                case 6:
                    System.out.println("请录入新的price的数据:");
                    data.setPrice(InputUtil.input.nextFloat());
                    break;
            }

        }
        System.out.println(goodsService.updateGoodById(data));

    }

    @Override
    public void select() {
        goodsService.selectAllGood().getData().forEach(System.out::println);
    }
}
