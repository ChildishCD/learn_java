package com.java.intro.interfaces.base;

@FunctionalInterface
//表示接口中有且仅有一个方法
//可以用lambda写法
public interface IDetailInfo {
    //成员变量
    //变量：默认用 public final static 修饰（常量）
    //常量的命名规范为全部大写，单词之间用_连接
    public final static int DETAIL_FLAG = 1;
    //根据这个特性，我们可以使用接口，作为 常量类来使用

    //final
    //- 修饰类: 类不能被继承
    //- 修饰方法:在继承的关系下，方法不能被重写
    //- 修饰变量:为常量

    //无构造方法

    //抽象方法
    //方法：默认 就是public abstract 修饰的 抽象方法（必须重写接口的全部方法）
    //只规定详细信息的获取方法
    //所有符合这个规范的对象才能使用这个方法,即对象必须重写此方法
    public abstract String detail();

    //功能方法（default）8+
    //功能方法，是可以在接口中  写带方法体的方法
//    default String test2(){
//        return "";
//    }

    //静态方法8+
    //默认是 public 调用方式，和 class中的 静态方法一致
    //使用场景不多
//    static void test3(){
//        System.out.println("静态方法");
//    }

    //私有化静态方法9+
    //为了 静态方法功能方法的补充
    //正常的 接口方法 是不能private的
    //使用场景不多
//    private static void test4(){
//        System.out.println("私有化静态方法");
//    }
}
