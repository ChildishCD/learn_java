package com.java.intro.basicclass.enumeration;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        //Put user info into an interface which have the constant information
        //--> LoginConstant

        //Create a class to store user information class
        //Use private modify the constructor
        //Use public final static modify the classes in the information class
        //--> LoginInfo

        //Create an enum class
        //Use enum replace class to declare an enum class
        //Use "," to divide enum member
        //enum need a ";" under the enum member in the enum class
        //Constructor default type is private
        //--> LoginInfoEnum
        System.out.println(LoginInfoEnum.SUPER_ADMIN.getName());

        //loop to output enum class
        //(enum).values() will return an array of all the enum member
        //(enum).valueOf("one of the enum member name")
        for(LoginInfoEnum loginInfoEnum : LoginInfoEnum.values()){
            System.out.println(loginInfoEnum.getRole());
        }
        System.out.println(LoginInfoEnum.valueOf("ADMIN").getRole());

        //enum can also do the things that a normal class can do
        // implement interface, add public methods
        // but can't extend other class(already extend Enum)
    }
}
