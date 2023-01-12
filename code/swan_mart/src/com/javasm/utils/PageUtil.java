package com.javasm.utils;

public class PageUtil {

    private PageUtil(){}

    public static Integer getStart(Integer pageNum,Integer pageSize){
        int start = (pageNum - 1) * pageSize;
        return start;
    }

}
