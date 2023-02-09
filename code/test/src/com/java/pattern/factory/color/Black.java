package com.java.pattern.factory.color;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Black implements Color{
    @Override
    public void fill() {
        System.out.println("Black filled.");
    }
}
