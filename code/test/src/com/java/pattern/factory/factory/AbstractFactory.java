package com.java.pattern.factory.factory;

import com.java.pattern.factory.color.Color;
import com.java.pattern.factory.shape.Shape;

public abstract  class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
