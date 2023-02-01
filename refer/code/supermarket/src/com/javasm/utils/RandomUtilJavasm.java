package com.javasm.utils;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomUtilJavasm {

    private RandomUtilJavasm() {
    }

    //获取指定长度的随机字符串
    public static String getRandomString(Integer num) {
        if (num == null || num < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String baseStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < num; i++) {
            Random random = new Random();
            int index = random.nextInt(baseStr.length());
            sb.append(baseStr.charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomInt(Integer num) {
        if (num == null || num < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            Random random = new Random();
            int index = random.nextInt(10);
            sb.append(index);
        }
        return sb.toString();
    }

    //根据时间生成随机文件名(无文件类型后缀)，5位随机字母+当前时间的年月日时分秒毫秒数+3位随机数字
    public static String generateFileName() {
        StringBuilder sb = new StringBuilder();
        //5位随机字母
        sb.append(getRandomString(5));
        //当前时间的年月日时分秒毫秒数
        sb.append(DateUtilJavasm.timeToString(LocalDateTime.now(),"yyyyMMddHHmmssSSS"));
        //3位随机数字
        sb.append(getRandomInt(3));
        return sb.toString();
    }


}
