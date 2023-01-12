package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Apple {
    private String color;
    private Double weight;

    //predicate(谓词) 接口
    //在数学上常常用来表示类似函数的东西
    //它接受一个参数值，并返回boolean
    //（也允许Function<Apple,Boolean>,但会把boolean封装在Boolean中）
//    public interface Predicate<T>{
//        boolean test(T t);
//    }
    public static List<Apple> filterApple(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static boolean isAppleRed(Apple apple) {
        return "red".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 200;
    }

    public Apple(String color, Double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
