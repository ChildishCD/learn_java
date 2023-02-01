package com.javasm.user.show;

import com.javasm.App;
import com.javasm.buy.show.BuyShow;
import com.javasm.goods.show.GoodsShow;
import com.javasm.goods.show.GoodsTypeShow;
import com.javasm.member.show.MemberShow;
import com.javasm.order.show.OrderShow;
import com.javasm.order.show.RankShow;
import com.javasm.user.model.SmMenuModel;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MenuShow {
    public static void viewMenu() {
        System.out.println("################");
        //这里动态输出菜单，对应JDBC作业(登录之后，显示欢迎xxx登录，显示用户角色，显示用户可以操作的菜单列表)的内容
        //题目没有要求这么麻烦，静态显示也可以
        List<SmMenuModel> menuList = App.userModel.getRole().getMenuList();
        //list转map之后 方便获取值，不需要循环
        //之前都是用的groupingBy 分组，这个是不分组直接转map，Map<主键, 对象>
        Map<Integer, SmMenuModel> menuMap = menuList.stream().collect(Collectors.toMap(SmMenuModel::getMid, Function.identity()));
        menuList.forEach(menu ->{
            System.out.println(menu.getMid() + "："+menu.getName());
        });
        System.out.println("-1：退出系统");
        System.out.println("#####请选择菜单#####");
        //这里只能显示菜单信息，无法直接控制跳转到哪个方法，所以还是和以前一样
        int num = App.scanner.nextInt();
        if (num == -1){
            System.exit(-1);
        }
        //显示对应的二级菜单
        SmMenuModel menuModel = menuMap.get(num);
        if (menuModel == null){
            //说明不是刚刚打印的菜单信息，返回
            return;
        }
        //获取二级菜单列表
        List<SmMenuModel> childList = menuModel.getChildList();

        switch (num){
            case 10:
                GoodsTypeShow.start(childList);
                break;
            case 11:
                GoodsShow.start(childList);
                break;
            case 12:
                MemberShow.start(childList);
                break;
            case 13:
                BuyShow.start();
                break;
            case 14:
                OrderShow.start();
                break;
            case 15:
                RankShow.start();
                break;
            default:
                break;
        }
    }

    //后续  可能 会 循环 显示 菜单  所以 把显示菜单 的代码 封装到一个方法里
    /*public static void viewMenu(){
        System.out.println("1--商品类型管理");
        System.out.println("2--商品管理");
        System.out.println("3--会员管理");
        System.out.println("4--购买管理");
        System.out.println("5--订单查询");
        System.out.println("6--排行统计");
        System.out.println("请选择菜单：");
        int num = scanner.nextInt();
        switch (num){
            case 1:
                //调用 商品类型 管理的 方法
                break;
            case 2:
                GoodsShow.start();
                break;
        }
    }*/
}
