package com.java.intro.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        //Stream 是针对数据处理的类，可以从多种数据类型获得
        //获取Stream类后可执行多种方法用来处理 来源（source）的数据
        //元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果
//                +--------------------+       +------+   +------+   +---+   +-------+
//                | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
//                +--------------------+       +------+   +------+   +---+   +-------+
        //以处理方式归并,将需要处理的数据合并为同一流,提高处理的效率


        //获得stream对象
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);//将数组转为list 的方法
        List<Integer> list2 = new ArrayList<>();//构造函数创建
        Collections.addAll(list2, 10, 20, 30, 40, 50);
        List<Integer> list3 = list2;
        //  用Stream.of方法合并为Stream
        //  合并两个list
        Stream<List<Integer>> listBoth= Stream.of(list1,list2);
        listBoth.forEach(System.out::println);
        //  转换
        List<Integer> listAll =
                Stream
                        .of(list1, list2, list3)//转为三个list流
                        .flatMap(new Function<List<Integer>, Stream<Integer>>() {//展开list中的元素为Integer流
                            @Override
                            public Stream<Integer> apply(List<Integer> integers) {
                                return integers.stream();
                            }
                        })
                        .collect(Collectors.toList());//集合元素为一个list
        //  Arrays的Stream的方法
        Integer[] array = {2020, 12, 31};
        Stream<Integer> stream = Arrays.stream(array);

        //Stream的使用
        Random random = new Random();
        //随机输出5个0-1000的整型数字
        //这是source为是个随机数的stream
        IntStream intStream = random.ints(50, 0, 1000);
        intStream
                .filter(x -> {
                    return x < 100;
                })
                .mapToDouble(Math::cos)
                .sorted()
                .forEach(System.out::println);
    }
}
