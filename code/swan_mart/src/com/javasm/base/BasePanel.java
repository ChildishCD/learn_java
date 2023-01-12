package com.javasm.base;

import com.javasm.banner.AdBanner;

import java.lang.reflect.Method;
import java.util.*;

public abstract class BasePanel {
    //每个面板一个Scanner
    protected static Scanner scanner = new Scanner(System.in);
    //当前面板是否运行
    protected String go = "Y";
    private Map<String, Method> methods = new HashMap<>();
    //存放导航信息
    protected Stack<String> naviBar = new Stack<>();

    public BasePanel() {
        initPanel();
    }

    //一般的列表式面板
    protected void listPanel(String name, BasePanel p, String[][] info) {
        //想导航中添加当前面板名
        naviBar.push(name);
        while (go.equals("Y") || go.equals("y")) {
            //输出广告
            AdBanner.printSeparator();
            //输出导航栏
            printNaviBanner();
            //输出选择列表
            for (int i = 0; i < info.length; i++) {
                String index = String.valueOf(i +1);
                System.out.println(index +"--"+info[i][0]);
                //找到每一项对应的面板方法
                try {
                    methods.put(index,p.getClass().getDeclaredMethod(info[i][1]));
                } catch (NoSuchMethodException e) {
                   e.printStackTrace();
                }
            }
            System.out.println("您好,请选择菜单：(输入其他字符退出)");
            String choice = scanner.next();
            //判断并执行输入的面板
            if (methods.containsKey(choice)) {
                try {
                    methods.get(choice).setAccessible(true);
                    methods.get(choice).invoke(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //询问是否结束当前面板
                System.out.println("检测到存在字符,您希望继续吗: (Y/N)");
                go = scanner.next();
            }
        }
        //回退导航
        naviBar.pop();
        initPanel();
    }
    protected void printNaviBanner(){
        System.out.println("您目前在: "+String.join("/",naviBar));
    }
    protected void initPanel() {
        go = "Y";
        methods.clear();
    }
}
