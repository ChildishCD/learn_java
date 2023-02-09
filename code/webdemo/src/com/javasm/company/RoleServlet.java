package com.javasm.company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//通过注解识别servlet(servlet 3.0及以上)
//这是简化写法,还有其他的参数-->看笔记
@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    //HttpServlet 可以自主选择重写哪一个方法, 该类继承自GenericServlet
    //常用doGet() 和 doPost()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用Get方法提交的执行这个方法
        //  form默认使用get方法
        //  地址栏直接输入地址使用get方法(a标签跳转,js跳转)
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用Post方法提交的执行这个方法

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/http;charset=UTF-8");

        String username = req.getParameter("username");
        String key = req.getParameter("key");
        String roleCodeStr = req.getParameter("role");
        int roleCode = 0;
        if (roleCodeStr != null) {
            roleCode = Integer.parseInt(roleCodeStr);
        }

        new Thread(()->{
            //可以有其他的操作
        }).start();

        String[] roleName = {"未知","超级管理员","管理员","开发人员","测试"};
        PrintWriter writer = resp.getWriter();
        writer.println("用户名: "+username);
        writer.println("密码: "+key);
        writer.println("权限: "+roleName[roleCode]);
        //重定向等...
//        resp.sendRedirect("https://bing.com/");
    }
}
