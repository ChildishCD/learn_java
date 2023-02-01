package com.javasm;

import com.javasm.goods.show.GoodsShow;
import com.javasm.user.model.SmUserModel;
import com.javasm.user.show.LoginShow;
import com.javasm.user.show.MenuShow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//程序的唯一入口
public class App {
    //登录之后的用户信息，存入这里，用来判断是否需要再次登录
    //如果null，说明没有登录，如果非空，说明已经登录
    public static SmUserModel userModel;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //用户进入程序 应该看见的内容是什么
        System.out.println("-----欢迎来到尚马超市管理系统-----");
        while (true){
            //登录之后才能操作
            if (userModel == null){
                //判断是否登录，如果未登录，跳转登录操作
                LoginShow.login();
            }else {
                //显示菜单信息
                MenuShow.viewMenu();
            }
        }


    }
}
