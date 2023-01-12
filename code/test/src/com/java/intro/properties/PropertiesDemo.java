package com.java.intro.properties;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//某处 新建test.properties 文件
//然后对其进行读取
public class PropertiesDemo {
    public static void main(String[] args) {
//        test1();
//        test2();
        //test3(); //store the properties
    }

    private static void test2() {
        //load the .properties file
        Properties properties = new Properties();
        //一般写相对路径, 默认的目录是项目的目录
        try (FileInputStream stream = new FileInputStream("src/test.properties")){
            properties.load(stream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test1() {
        //create a Properties
        //the essence is a map
        Properties p = new Properties();
        p.put("name","root");
        p.put("age",52);
        //.getProperty() give String
        String name = p.getProperty("name");
        //.get give Object
        Integer age = (Integer)p.get("age");
        System.out.println(name + age);
    }
}
