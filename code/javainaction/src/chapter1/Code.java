package chapter1;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class Code {

    public static void main(String[] args) {
        //->发散
        //  方法返回的类型是boolean
        //  传入的方法中可以注入其他的操作，如对其他数据的操作、打印数据...
        methodDeliverTest();
        streamTest();
    }

    private static void streamTest() {
        
    }

    private static void methodDeliverTest() {
        //->概念：
        //  一等值
        //      类实例，基本类型等直接作用的值
        //  二等值
        //      方法，类，判断条件等概念
        //  java能将二等值作为一等值进行操作，让编程更加容易
        //  stream，lambda
        List<Apple> apples = new ArrayList<>();
        //方法的传入
        //->旧：传入方法时，需要把方法包裹在一个 比较器 对象中才能传入
        //  根据重量给list中的apple排序
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return (int) (o1.getWeight() - o2.getWeight());
            }
        });
        //->新：使用 类::方法 直接传入方法
        apples.sort(Comparator.comparing(Apple::getWeight));
        //->Apple类中利用Predicate自己编写一个可以传入方法的方法
        List<Apple> redApples = Apple.filterApple(apples, Apple::isAppleRed);
        //->新：使用 lambda 语法 直接定义函数 并传入
        //  该方法用 lambda 语法定义了函数
        //  表示 调用时给定参数 x 就返回 x+1 的值
        IntFunction<Integer> integerIntFunction = (int x) -> x + 1;
        System.out.println(integerIntFunction.apply(1));
        List<Apple> badApples = Apple.filterApple(apples,(Apple a) -> a.getWeight()<80 && "green".equals(a.getColor()));
        //->调用已有库中的方法
        //->顺序处理
        List<Apple> bigApples = apples.stream().filter(Apple::isHeavyApple).collect(Collectors.toList());
        //->并行处理
        //  这个"几乎免费"的并行,只有在数据之间没有互动时才能生效
        //  但把函数作为一等值,常常包含了执行时利用该方法,数据间没有互动
        bigApples = apples.parallelStream().filter(Apple::isHeavyApple).collect(Collectors.toList());
    }

}
