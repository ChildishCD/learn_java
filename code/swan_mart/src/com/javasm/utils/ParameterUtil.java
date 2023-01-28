package com.javasm.utils;

public class ParameterUtil {

    private ParameterUtil(){}

    //可变参数  调用的时候，可以传入任意多的参数
    //方法内部 objects 被看作是一个 数组
    //给当前的方法 具体一下返回值的 意义
    // 如果 参数 都合格 返回true 否则 返回false
    public static boolean checkParameter(Object... objects){
        for (Object o : objects){
            if (o == null){
                return false;
            }
            if (o instanceof String){
                if (o.toString().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
}
