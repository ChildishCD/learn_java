package com.javasm.user.show;

import com.javasm.App;
import com.javasm.user.model.SmUserModel;
import com.javasm.user.service.LoginService;

import java.util.Scanner;

//登录、注册
public class LoginShow {
    private static LoginService loginService = new LoginService();
    public static void login() {
        System.out.println("-----请登录-----");
        System.out.println("请输入用户名：");
        String username = App.scanner.next();
        System.out.println("请输入密码：");
        String password = App.scanner.next();
        //传入用户名密码查询用户信息
        SmUserModel smUserModel = loginService.checkLogin(username,password);
        if (smUserModel !=null){
            System.out.println("登录成功！");
            //把查询到的用户信息，存到App类的全局变量中，方便使用
            App.userModel = smUserModel;
        }else {
            System.out.println("登录失败！");
        }
        //方法执行完会自动返回到main方法的while(true)循环中
    }
}
