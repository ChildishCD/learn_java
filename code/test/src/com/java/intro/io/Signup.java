package com.java.intro.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Signup {
    public static void main(String[] args) {
//        signup();
//        System.out.println(getAllUser());
        login();
    }

    private static Integer id = 1000;
    private static String path = "user.txt";

    //get user information
    private static List<User> getAllUser() {
        //get all user's information and add to a list
        List<User> users = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] split = temp.split("-");
                //check user row in .txt is legal!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                if (split.length == 4) {
                    users.add(new User(Integer.valueOf(split[0]), split[1], split[2], Integer.valueOf(split[3])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private static void login() {
        //input name, pass
        //check user existence
        System.out.println("-----------STARTING LOG IN PROCEDURE-----------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please your name:");
        String name = scanner.next();
        System.out.println("Please your password");
        String pass = scanner.next();
        List<User> users = getAllUser();
        //转为map！！方便用户的获取！！！！！！！！！！！！！！！！！！！！！！！！！！！
        Map<String, User> usersMap = users.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        User one = new User(name, pass);
        if (users.contains(one)) {
            System.out.println("User:" + usersMap.get(name).toString());
            System.out.println("Log in successfully!");
        } else {
            System.out.println("The name or password may be wrong!");
        }
    }

    //sign up
    private static void signup() {
        System.out.println("-----------STARTING SIGH UP PROCEDURE-----------");
        //input Y/N to continue
        //input name,pass,age and give an id
        //save info to user.txt formatting "id-name-pass-age"
        String isContinue = "Y";
        Scanner scanner = new Scanner(System.in);
        try (FileWriter fileWriter = new FileWriter(path, true);) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while ("Y".equals(isContinue) || "y".equals(isContinue)) {
                System.out.println("Please input your name:");
                String name = scanner.next();
                System.out.println("Please input your password:");
                String pass = scanner.next();
                System.out.println("Please input your age:");
                Integer age = scanner.nextInt();
                //formatting the information string
                //you can design a class when the ways can put in a class!!!!!!!!!!!
                List<User> users = getAllUser();
                //get maximum user id
                //make user id auto increase from the maximum id
                if (users.size() > 0) {
                    id = users.get(users.size() - 1).getId() + 1;
                }
                User one = new User(id++, name, pass, age);
                //check the input user existence
                if (users.contains(one)) {
                    System.out.println("The user is already exist!");
                } else {
                    bufferedWriter.write(one.toString());
                    bufferedWriter.newLine();
                    //save the information
                    bufferedWriter.flush();
                }
                //ask if continue
                System.out.println("Do you want add another user?(Y/N)");
                isContinue = scanner.next();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
