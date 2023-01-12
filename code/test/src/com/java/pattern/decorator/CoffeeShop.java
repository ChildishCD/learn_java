package com.java.pattern.decorator;

import com.java.pattern.decorator.coffee.Latte;
import com.java.pattern.decorator.topping.Bubble;
import com.java.pattern.decorator.topping.Sugar;

public class CoffeeShop {
    public static void main(String[] args) {
        Drink latte = new Latte();
        System.out.println(latte.getName() + " -- " + latte.sumPrice());
        latte = new Bubble(latte);
        System.out.println(latte.getName() + " -- " + latte.sumPrice());
        latte = new Sugar(latte);
        System.out.println(latte.getName() + " -- " + latte.sumPrice());
    }
}
