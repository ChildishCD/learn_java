package com.javasm.base;

import com.javasm.banner.AdBanner;

import java.lang.reflect.Method;
import java.util.*;

public class BasePanel<T> extends Base<T> {
    //每个面板一个Scanner
    protected static Scanner scanner = new Scanner(System.in);
    //每一个面板一个service
    protected BaseService service;
    //当前面板是否运行
    private String go = "Y";
    protected String go2 = "N";
    private Map<String, Method> methods = new HashMap<>();
    private List<String> inputs = new ArrayList<>();
    //存放导航信息
    protected Stack<String> naviBar;

    public BasePanel() {
        service = null;
        naviBar = new Stack<>();
    }

    public BasePanel(BasePanel prePanel, BaseService service) {
        this.service = service;
        naviBar = prePanel.naviBar;
    }

    //打印导航栏信息
    protected void printNaviBanner() {
        System.out.println("您目前在: " + String.join("/", naviBar));
    }

    //初始化重复利用的变量
    protected void initPanel() {
        go = "Y";
        go2 = "N";
        methods.clear();
    }

    protected void clearPanel(){
        inputs.clear();
        service.initService();
    }

    //一般的列表式面板
    public void listPanel(String name, BasePanel p, String[][] info) {
        //想导航中添加当前面板名
        naviBar.push(name);
        while (checkGo(go)) {
            //输出广告
            AdBanner.printSeparator();
            //输出导航栏
            printNaviBanner();
            //输出选择列表
            for (int i = 0; i < info.length; i++) {
                String index = String.valueOf(i + 1);
                System.out.println(index + "--" + info[i][0]);
                //找到每一项对应的面板方法
                try {
                    methods.put(index, p.getClass().getDeclaredMethod(info[i][1]));
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

    //sql面板
    public void sqlPanel(String name, String methodName, String[] fieldNames) {
        sqlPanel(name, methodName,false, false, fieldNames);
    }

    public void sqlPanel(String name, String methodName,boolean id ,boolean page, String[] fieldNames) {
        naviBar.push(name);
        int start = 0;
        while (checkGo(go)) {
            AdBanner.printSeparator();
            printNaviBanner();
            if(id){
                idCheckPanel();
            }
            //输入数据
            if (fieldNames.length != 0) {
                for (String fieldName : fieldNames) {
                    System.out.println("请输入" + fieldName + ":");
                    inputs.add(scanner.next());
                }
            } else if (page) {
                inputs.add(0, String.valueOf(start));
            }

            do {
                //将数据传入service并输出
                service.results.clear();
                try {
                    this.service.getClass().getMethod(methodName, List.class).invoke(service, inputs);
                    if (service.checkResults()) {
                        System.out.println("操作成功！");
                        service.results.forEach(System.out::println);
                        clearPanel();
                    } else {
                        System.out.println("无结果！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("输入有误！");
                }
                if (page) {
                    System.out.println("您希望查看下一页吗: (Y/N)");
                    if (checkGo(scanner.next())) {
                        start += 10;
                        inputs.add(0, String.valueOf(start));
                    } else {
                        go = "N";
                        break;
                    }
                }
            } while (page);

            if (!page) {
                //询问是否结束当前面板
                System.out.println("您希望继续吗: (Y/N)");
                go = scanner.next();
            }
        }
        naviBar.pop();
        initPanel();
    }

    public void idCheckPanel() {
        naviBar.push("检查id是否存在");
        AdBanner.printSeparator();
        printNaviBanner();
        System.out.println("请输入id");
        int id = Integer.parseInt(scanner.next());
        service.results.add(service.selectById(id));
        if (service.checkResults()) {
            System.out.println("操作成功！");
            service.results.forEach(System.out::println);
            go2 = "Y";
            inputs.add(String.valueOf(id));
        } else {
            System.out.println("无结果！");
        }
        naviBar.pop();
    }

    public void deletePanel() {
        naviBar.push("删除");
        while (checkGo(go)) {
            idCheckPanel();
            if (checkGo(go2)) {
                System.out.println("确定删除以上条目吗：(Y/N)");
                if (checkGo(scanner.next())) {
                    service.deleteById(Integer.valueOf(inputs.get(0)));
                    clearPanel();
                }
                System.out.println("删除成功！");
            }
            System.out.println("您希望继续吗: (Y/N)");
            go = scanner.next();
        }
        naviBar.pop();
        initPanel();
    }

    public boolean checkGo(String s) {
        return s.equals("Y") || s.equals("y");
    }

}
