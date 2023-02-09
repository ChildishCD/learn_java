package com.java.intro.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

public class Demo {
    public static void main(String[] args) throws Exception {
        URLConnect();
    }

    private static void URLConnect() throws Exception {
        // 获取主机对象
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println(localhost.getHostName());
        System.out.println(localhost.getHostAddress());

        // 查询天气的免费接口
        // 可以搜寻其他的接口
        URL url = new URL("http://t.weather.itboy.net/api/weather/city/101270101");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        //读取单行 通常为服务接口返回数据 纯数据 不带换行
        String s = br.readLine();
        System.out.println(s);
        //读取多行 可以读取完整页面数据
        //读到之后可以根据字符串规则查找数据中的图片 文字等内容
        s = "";
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
    }
}
