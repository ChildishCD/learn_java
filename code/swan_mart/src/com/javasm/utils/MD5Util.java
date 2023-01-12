package com.javasm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    //禁止new对象
    private MD5Util() {
    }

    private static String SALT = "javasm*^%_";

    /**
     * md5加密
     *
     * @param sourceStr 原数据
     * @return 加密之后的结果
     */
    public static String md5(String sourceStr){
        if (sourceStr == null || sourceStr.isEmpty()) {
            throw new NullPointerException();
        }
        StringBuilder builder = new StringBuilder();

        try {
            //1.获得信息摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            sourceStr = SALT + sourceStr;
            //2. 更新摘要算法---->将加密数据传到信息摘要对象中
            messageDigest.update(sourceStr.getBytes());

            //3.加密
            byte[] bytes = messageDigest.digest();//加密之后的结果

            for (byte aByte : bytes) {
                builder.append(byteToHex(aByte));
            }
            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //一个字节---2个16进制里面字符
    private static String byteToHex(byte aByte) {//-128-127
        int num = aByte;
        if (aByte < 0) {
            num = aByte + 256;
        }
        int highIndex = num / 16;
        int lowIndex = num % 16;

        return array[highIndex] + array[lowIndex];
    }

    private static final String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
