package com.java.intro.basicclass.list;

import java.util.*;
import java.util.function.Predicate;

public class Sheet {
    public static void main(String[] args) {
        //list,set,map除了可以存储数据,还可以存储类实例
        //存储,去重,排序
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        test8();
    }

    private static void test8() {
        //List数据进行分组，以泛型某个属性为分组依据，分组为map，key是某个属性，value是List
        List<One> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(new One());
        }
        System.out.println(list);
    }

    private static void test7() {
        //排序
        List<One> list = new ArrayList<>();
        list.add(new One("a",20));
        list.add(new One("b",25));
        list.add(new One("c",24));
        list.add(new One("d",23));
        //使用重写的 比较 方法排序 实现 Comparable<T> 接口 赋予类排序的能力
        Collections.sort(list);//重写方法对成绩排序
        list.sort(One::compareTo);
        //使用 比较器 排序; 多数情况未实现 比较接口 的类，都能传入 比较器.ing(T::fuction) 获得比较
        Collections.sort(list,(Comparator.comparing(One::getName)));//对名字排序
        //使用 （List）.sort方法排序
        list.sort((Comparator.comparing(One::getName).reversed()));//对名字排序且是逆序

        System.out.println(list);
        //利用set排序
        TreeSet set = new TreeSet<>(list);//实现了比较接口的list可以直接传入
        System.out.println(set);

        //继承Comparable接口
        //....implements Comparable<One>{}
        //orverride compare to
        //  this - o 为正
        //根据compareTo() 函数排序

        //排序函数中的输入参数有comparator
        //即直接调用实例的compareTo方法制定比较的规则

//        Collection.sort();
    }

    private static void test6() {
        String str = "The quick brown fox jumps over a lazy dog";
        Map<Character, Integer> map = new HashMap<>();
        //merge方法
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.merge(c,1,Math::addExact);
        }
        System.out.println(map);
        System.out.println(map.size());
        //compute方法
//        for (String key : goods.keySet()){
//            goods.compute(key,(k,v)->{
//                return v * 0.9;
//            });
//        }
    }


    private static void test5() {
        //Map;HashMap,ConcurrentHashMap
        //HashMap是非线程安全的
        //ConcurrentHashMap 是线程安全的
        //Map<K,V> key 和 value,key是唯一的标识
        Map<Integer, String> hashMap = new HashMap();
        //Map map = new ConcurrentHashMap();
        //key尽量简单，尽量使用基本数据类型
        //添加数据
        hashMap.put(001, "Tom");
        hashMap.put(002, "Jack");
        hashMap.put(002, "Ape");//已存在的key,后写的会直接覆盖
        hashMap.put(003, "York");
        hashMap.putIfAbsent(001, "Harry");//key未使用的时候才会被写入
        //map的操作围绕key
        hashMap.remove(002);//通过key删除数据
        hashMap.get(001);
        //key是用set储存的
        Set<Integer> key = hashMap.keySet();
        //value是用collection存储的
        Collection<String> values = hashMap.values();
        //map的遍历
        //  可以通过遍历keyset()或values()遍历
        //  直接用map的foreach
        hashMap.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
    }

    private static void test4() {
        //---
        //数据自动升序排序（排序不是它的功能，具体实现请用排序相关类）
        //不能为null
        //实际为TreeMap
        //排序由Comparator类实现,传入的类必须要实现comparable接口
        TreeSet<One> treeSet = new TreeSet<>();
        treeSet.add(new One("Hades", 90));
        treeSet.add(new One("Hades", 50));
        treeSet.add(new One("Shirley", 60));
        treeSet.add(new One("Mike", 80));
        System.out.println(treeSet);
    }

    private static void test3() {
        //---
        //HashSet
        //实际用HashMap存储，成员在HashMap中的key内，value值是一个空的对象
        //方法也是用的HashMap的方法
        Set<String> set = new HashSet<>();
        set.add("Hello");
        set.add("你好");
        set.add("你好");
        set.add("Hi");
        System.out.println(set);
        //条件删除，删除为”Hi“的成员
        set.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("H");
            }
        });
        set.removeIf(sss -> {
            return sss.contains("H");
        });
        set.removeIf("H"::contains);
        set.removeIf("H"::equals);
        System.out.println(set);
        //遍历
        //增强型for循环
        for (var p : set) {
            System.out.println(p);
        }
        //迭代器
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //foreach 1
        set.forEach(s -> {
            System.out.println(s);
        });
        //foreach 2
        set.forEach(System.out::println);
    }

    private static void test2() {
        //------------------------------------------------
        //set:HashSet,TreeSet,LinkedHashSet
        //无序且不可重复的
        //主要用于去重(保持唯一)
        //数据根据哈希值排序，故存取的顺序不一致
        //Set中判断两个成员是否相等(去重)由存入类的equals方法确定, 可以重写类的equals方法

        //---
        //LinkedHashSet
        //HashSet是它的父类
        //有序且不可重复的
        //实际上是LinkedHashMap
        //可以有null值
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        //方法同上

    }

    private static void test1() {
        //动态数组
        //数据可以重复
        List<ManyOne> sheet = new ArrayList<>();
        sheet.add(new One());
        sheet.add(new One());
        sheet.add(new Two());
        sheet.add(new Two());
        for (ManyOne p : sheet) {
            p.show();
        }
        //双向链表
        List<ManyOne> list = new LinkedList<>();
        list.add(new One());
        list.add(new One());
        list.add(new Two());
        list.add(new Two());
        //其他操作
//        list.remove();
//        list.removeAll();
//        list.removeIf();
//        list.get(2);
//        list.set(2,new Two());
//        list.isEmpty();
//        list.size();
    }
}
