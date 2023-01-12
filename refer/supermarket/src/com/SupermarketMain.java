package com;

import com.javasm.util.InputUtil;
import com.javasm.view.impl.BuyViewImpl;
import com.javasm.view.impl.GoodViewImpl;
import com.javasm.view.impl.TypeViewImpl;

/**
 * @author: Lisa
 * @className: SupermarketMain
 * @description:
 * @date: 2021/6/15 10:02
 * @version: 0.1
 * @since: 1.8
 */
public class SupermarketMain {


    public void start() {
        while (true) {
            System.out.println("1.商品类型管理");
            System.out.println("2.商品管理");
            System.out.println("3.会员管理");
            System.out.println("4.购买管理");
            System.out.println("5.订单管理");
            System.out.println("6.排行管理");
            System.out.println("7.退出管理");

            int choice = InputUtil.inputInt("^[1-7]{1}$", "请录入1-7的数字:");
            switch (choice) {
                case 1:
                    new TypeViewImpl().menu();
                    break;
                case 2:
                    new GoodViewImpl().menu();
                    break;
                case 3:
                    break;
                case 4:
                    new BuyViewImpl().menu();
                    break;
                case 5:
                    break;
                case 6:
//                    CREATE TABLE aa SELECT
//                    g.good_name,g.price,t.type_name,sum(od.buy_num) num
//                        FROM
//                    goods g,type t, orderdetail od,orderinfo o
//                    WHERE g.type_id=t.id AND  od.gid=g.id AND od.oid=o.id AND MONTH(o.pay_time)=? AND t.id=?
//                    GROUP BY g.id ORDER BY num DESC LIMIT 10;
                    break;
                case 7:
                    System.out.println("程序终止");
                    return;
            }
        }

    }
}
