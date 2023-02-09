package com.java.pattern.factory.shape;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Point implements Shape{
    @Override
    public void draw() {
        System.out.println("This is a point.");
    }
}
