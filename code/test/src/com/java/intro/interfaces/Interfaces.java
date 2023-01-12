package com.java.intro.interfaces;

import com.java.intro.interfaces.base.*;

public class Interfaces {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        //单独声明接口,有以下几种实现方式
        //lambda表达式（仅用于FunctionalInterface或接口只有一个抽象方法）R语言常用
        //只是代码的一种写法
        //()表示参数
        //  无参,()
        //  有参,(a)一个参数可以省略()
        //->表示:
        //  无返回值,void声明的函数可以写方法体{}
        //  有返回值,return后的式子,无方法仅有一个返回值或一行代码可以省略{},

        //IDetailInfo detailInfo = ()-> {return "Showing the detail now...";};
        //IDetailInfo detailInfo = ()-> return "Showing the detail now...";
        IDetailInfo detailInfo = new PM("Harry",23)::toString;
        System.out.println(detailInfo.detail());//调用接口实例的lambda方法
        String name = "Harry";

        ISameName test = name::equals;//简化的重写定义接口的类实例和它的方法,方法的返回值一致
        System.out.println(test.isSame("Jerry"));

        //匿名内部类
        ICaseInfo caseInfo = new ICaseInfo() {
            @Override
            public void showCase() {
                System.out.println("I have my personal doctor!");
            }

            @Override
            public String detail() {
                return null;
            }
        };
        caseInfo.showCase();//内部类的方法
        //构造后转换
        IDetailInfo Oracle = new Company();
        Oracle.detail();
    }

    private static void test1() {
        //软件接口
        //宏观的体现比如PS5和PC的游戏只能在指定的平台运行
        //具体是因为实现的方式遵循了不同的规范
        PM Joseph = new PM("Joseph",30);
        Company IBM = new Company("IBM","Earth");
        IBM.printer = new Plotter();
        //PM实例符合IDetailInfo接口的方法;Printer.print传参为IDetailInfo接口
        //-->某类(可被继承) 某方法(可被覆写) 可以传入各种 不同 但都遵循了该接口 的对象
        IBM.printer.print(Joseph);
    }
}
