package com.javasm.goods.show;

import com.javasm.App;
import com.javasm.goods.model.GoodsModel;
import com.javasm.goods.model.GoodsTypeModel;
import com.javasm.goods.service.GoodsService;
import com.javasm.goods.service.GoodsTypeService;
import com.javasm.user.model.SmMenuModel;

import java.util.List;

//只负责 和 用户交互
//接收 用户输入信息
//打印 内容
public class GoodsShow {

    private final static GoodsService goodsService = new GoodsService();
    private final static GoodsTypeService goodsTypeService = new GoodsTypeService();

    public static void start(List<SmMenuModel> childList){
        String goon;
        do {
            //打印二级菜单
            childList.forEach(child -> System.out.println("\t"+child.getMid()+":"+child.getName()));

            System.out.println("请选择操作：");
            int num = App.scanner.nextInt();
            switch (num){
                case 1101:
                    add();
                    break;
                case 1102:
                    update();
                    break;
                case 1103:
                    query();
                    break;
                case 1104:
                    del();
                    break;
                default:
                    System.out.println("菜单编号错误");
                    break;
            }
            System.out.println("是否返回主菜单？y/n");
            goon = App.scanner.next();
        }while (goon.equals("n"));
    }

    private static void del() {
        viewGoodsList();

        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请选择要删除的商品id");
            int id = App.scanner.nextInt();
            goodsService.deleteGoods(id);
            System.out.println("删除成功，是否继续删除？y/n");
            goon = App.scanner.next();
        }
    }

    private static void query() {
        viewGoodsList();
        //列表把所有字段都显示了，没必要再查询单条了
    }

    private static void update() {
        viewGoodsList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请选择要修改的商品id");
            int id = App.scanner.nextInt();
            edit(id);
            System.out.println("修改成功,是否继续修改？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }

    private static void edit(Integer id){
        System.out.println("请输入商品名称：");
        String name = App.scanner.next();
        System.out.println("请输入商品价格：");
        Double price = App.scanner.nextDouble();
        System.out.println("请选择商品类型：");
        //查询 商品类型列表
        List<GoodsTypeModel> goodsTypeList = goodsTypeService.queryTypeList();
        StringBuilder sb = new StringBuilder();
        sb.append("\t| ");
        for (GoodsTypeModel type: goodsTypeList){
            sb.append(" ");
            sb.append(type.getId());
            sb.append(":");
            sb.append(type.getTypeName());
            sb.append(" |");
        }
        //不能调用MenuShow类中的显示方法，显示是在逻辑之前，不能向前调用
        System.out.println(sb);
        //查询 goodtype的列表，显示汉字，让用户选择id编号 存的是主键，但是不能让用户直接输入主键
        int type = App.scanner.nextInt();
        System.out.println("请输入商品数量：");
        Integer num = App.scanner.nextInt();
        System.out.println("请输入商品状态（1正常2下架3删除）：");
        Integer state = App.scanner.nextInt();
        System.out.println("请输入折扣 （1折输入1）：");
        Integer discount = App.scanner.nextInt();
        goodsService.edit(id,name,type,price,num,state,discount);

    }

    //分流 出来  和 用户交互的 方法
    private static void add(){
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请输入添加的商品信息");
            edit(null);
            System.out.println("添加成功,是否继续添加？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }

    public static void viewGoodsList(){
        List<GoodsModel> goodsModelList = goodsService.queryGoodsList();
        if (goodsModelList != null){
            System.out.println("\t|商品ID|商品名称|商品类型|价格|库存|状态|折扣|");
            for (GoodsModel goods : goodsModelList){
                System.out.printf("\t|%d|%s|%s|%s|%s|%s|%d|\n",
                        goods.getId(),
                        goods.getGoodsName(),
                        goods.getTypeName(),
                        goods.getPriceStr(),
                        goods.getInventory(),
                        goods.getStateStr(),
                        goods.getDiscount()
                );
            }
        }else {
            System.out.println("暂无商品");
        }

    }


}
