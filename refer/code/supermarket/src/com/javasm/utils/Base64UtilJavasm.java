package com.javasm.utils;

import java.util.Base64;

public class Base64UtilJavasm {

    private Base64UtilJavasm(){}

    //加密
    public static String encoder(String str){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes());
    }
    //解密
    public static String decoder(String encoder){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encoder));
    }
}
