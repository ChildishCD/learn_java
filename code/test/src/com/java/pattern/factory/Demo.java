package com.java.pattern.factory;

import com.java.pattern.factory.factory.ShapeFactory;
import com.java.pattern.factory.shape.Shape;

public class Demo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape point = shapeFactory.getShape("Point");
        point.draw();
    }
}
