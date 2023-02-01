package com.javasm.goods.show;

import com.javasm.App;
import com.javasm.goods.model.GoodsTypeModel;
import com.javasm.goods.service.GoodsTypeService;
import com.javasm.user.model.SmMenuModel;
import com.javasm.utils.DateUtilJavasm;

import java.util.List;

public class GoodsTypeShow {

    private static GoodsTypeService goodsTypeService = new GoodsTypeService();

    public static void start(List<SmMenuModel> childList){
        String goon = "n";
        do{
            //打印二级菜单
            childList.forEach(child -> System.out.println("\t"+child.getMid()+":"+child.getName()));

            System.out.println("--请选择菜单：");
            int num = App.scanner.nextInt();
            //分到不同的方法
            switch (num){
                case 1001:
                    add();
                    break;
                case 1002:
                    update();
                    break;
                case 1003:
                    query();
                    break;
                case 1004:
                    del();
                    break;
                default:
                    System.out.println("菜单编号错误");
                    break;
            }
            System.out.println("是否返回主菜单？y/n");
            goon = App.scanner.next();
        }while ("n".equals(goon));

    }

    private static void del() {
        viewTypeList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请输入删除的类别ID:");
            int id = App.scanner.nextInt();
            goodsTypeService.deleteType(id);
            System.out.println("删除成功，是否继续删除？y/n");
            goon = App.scanner.next();
        }
    }

    private static void query() {
        viewTypeList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请输入查询的类别ID:");
            int id = App.scanner.nextInt();
            GoodsTypeModel goodsTypeModel = goodsTypeService.queryTypeById(id);
            if (goodsTypeModel == null){
                System.out.println("查无此条消息，请输入正确的id");
            }else {
                System.out.println("\t| ID | 名称 | 创建时间 | 修改时间 | 子类型 |");
                System.out.printf("\t| %d | %s | %s | %s | \n",
                        goodsTypeModel.getId(),
                        goodsTypeModel.getTypeName(),
                        DateUtilJavasm.timeToStringAll(goodsTypeModel.getCreateTime()),
                        DateUtilJavasm.timeToStringAll(goodsTypeModel.getUpdateTime()));
                List<GoodsTypeModel> childList = goodsTypeModel.getChildTypeList();
                if (childList!=null){
                    System.out.println("\t\t\t--| ID | 名称 | 创建时间 | 修改时间 | 子类型 |");
                    for (GoodsTypeModel type : childList){
                        System.out.printf("\t\t\t\t--| %d | %s | %s | %s | \n",
                                type.getId(),
                                type.getTypeName(),
                                DateUtilJavasm.timeToStringAll(type.getCreateTime()),
                                DateUtilJavasm.timeToStringAll(type.getUpdateTime()));
                    }
                }
            }
            System.out.println("是否继续查询？y/n");
            goon = App.scanner.next();
        }
    }

    private static void update() {
        viewTypeList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("-----请输入需要修改的商品类别id-----");
            Integer id = App.scanner.nextInt();
            System.out.println("请输入类别名称");
            String name = App.scanner.next();
            System.out.println("请输入父id(没有父id输入0)");
            Integer pid = App.scanner.nextInt();
            goodsTypeService.updateType(id,pid,name);
            System.out.println("修改成功,是否继续添加？y/n");
            goon = App.scanner.next();
        }
    }

    private static void add() {
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("-----添加商品类型信息-----");
            System.out.println("请输入类型名称");
            String name = App.scanner.next();
            System.out.println("请输入父ID(没有父id输入0)");
            Integer pid = App.scanner.nextInt();
            //执行 添加
            goodsTypeService.addType(name,pid);
            System.out.println("添加成功,是否继续添加？y/n");
            goon = App.scanner.next();
        }

    }
    private static void viewTypeList(){
        List<GoodsTypeModel> goodsTypeModels = goodsTypeService.queryTypeList();
        System.out.println("\t|ID| 名称 | 子类型 |");
        for (GoodsTypeModel type : goodsTypeModels){
            System.out.printf("\t|%d| %s |\n",type.getId(),type.getTypeName());
            List<GoodsTypeModel> childList = type.getChildTypeList();
            if (childList != null){
                System.out.println("\t\t\t--|ID| 名称 |");
                for (GoodsTypeModel child: childList){
                    System.out.printf("\t\t\t--|%d| %s |\n",child.getId(),child.getTypeName());
                }
            }
        }
    }
}
