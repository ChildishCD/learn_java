package com.java.intro.oop;

/**
 * 权限修饰符 普通修饰符 返回值 方法名（参数）{
 * 方法体
 * }
 * 权限修饰符 public private protected （default）...
 * （default）同包可以用，跨包不能用
 * protected 同上，但子类可以
 * this 调用成员变量、调用构造、this为参数
 * static 静态变量、静态代码块
 * 只能执行一次（...）
 * 普通修饰符 static abstract final ...
 * final 表示将该成员变量声明为常量，其值无法更改
 */

public class Method {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    private static void test7() {
        //抽象类
        //在某些情况下，基类无法（或者没有必要）提供被覆盖方法的具体实现，那么就可以将此方法声明成抽象方法；
        //抽象类是方法的容器，如果某个类中包含有抽象方法，那么该类就必须定义成抽象类；（也可有普通方法）
        //通常抽象类作为父类，子类也可为抽象类
        //抽象类本身不可被new，必须通过子类调用
        //制定规则，提前规划项目的骨架，更多是应用在框架上
        //匿名内部类{}中的表示一个类
        //接口同理
        Animal ant = new Animal() {
            //抽象类的方法必须重写，继承同理
            @Override
            public double bodyAge() {
                return  this.height;
            }
        };
    }

    private static void test6() {
        //对象数组
        Person school[] = new Person[10];
        school[0] = new Student("Tom", 12, 2012);
        school[1] = new Leader("Christ", 32);
        //循环输出，增强型for循环
        for (Person p : school) {
            if (p != null) {
                p.show();
            }
        }
    }

    private static void test5() {
        //内部类
        Leader leader = new Leader("BossBad", 99);
        Leader.Supervisor supervisor = new Leader.Supervisor("ME");
    }

    private static void test4() {
        //单例
    }

    private static void test3() {
        //方法的重载
        //方法的重载仅考虑传入参数的数量、类别

        //方法可变参数
        //形参：String[] parents 和 String...parents 一样

        //类与方法的继承
        //详见Student类
        //父类爷爷类的属性都能用
        //object类是所有类的祖宗类
        //阻止继承、向上转型、向下转型：
        // https://www.liaoxuefeng.com/wiki/1252599548343744/1260454548196032
        Student s = new Student("kid", 12, 2030);
        Teacher t = new Teacher("elder", 60);
        System.out.println(t.getName());
    }

    private static void test2() {
        Person p = new Person("Jack Smith", 23);
        //构造时绑定参数(传递地址)
        //基本数据类型传递的是数值
        //引用数据类型传递的是地址（数组..）,如此方法才会起作用
        String[] parentsname = new String[]{"Homer", "Simpson"};
        p.setParents(parentsname); // 传入parentsname数组
        System.out.println(p.getParents()); // "Homer Simpson"
        parentsname[0] = "Bart"; // parentsname数组的第一个元素修改为"Bart"
        System.out.println(p.getParents()); // "Homer Simpson"还是"Bart Simpson"?

    }

    private static void test1() {
        //构造新的实例
        //构造方法没有普通修饰符
        //没有写构造方法，使用默认的空方法
        //如果写了带参数的方法，默认方法自动关闭，如需要则需定义一个空方法
        //没有在构造方法中初始化字段时，引用类型的字段默认是null，数值类型的字段用默认值，int类型默认值是0，布尔类型默认值是false
        Person p = new Person("Jack Smith", 23);
        p.setName("Jack Tylor");
        p.setAge(24);
        System.out.println(p.getAge());
    }
}
