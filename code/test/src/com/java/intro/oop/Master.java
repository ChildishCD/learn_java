package com.java.intro.oop;


public class Master extends Person{
    /**
     *要会手搓!!!!!!!!!!!!!!
     * @param name
     * @param age
     * 创建一个单例
     *  私有化构造方法 private
     *  创建一个自身的静态实例 private static
     *  创建getInstance方法，public static
     */
    //构造方法变为private，变成单例
    private Master(String name, int age) {
        super(name, age);
    }
    private Master(){};
    //懒汉式，需要的时候才创建
    //全局的一个静态成员变量，仅有一个地址
    //其他的类不能调用
    //仅声明，没有创建实例
    private static Master master;
    public static Master getInstance(){
        if(master == null){
            master = new Master();
        }
        return master;
    }
    //饿汉式，先创建
//    private static Master master = new master();
//    public static Master getInstance(){
//        return master;
//    }
}
