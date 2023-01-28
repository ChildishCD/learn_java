package com.javasm.base;

import java.lang.reflect.ParameterizedType;

public abstract class Base<T> {
    //获取 泛型T的 class对象
    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
