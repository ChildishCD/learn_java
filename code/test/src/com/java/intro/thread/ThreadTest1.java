package com.java.intro.thread;

public class ThreadTest1 extends Thread{
    @Override
    public void run() {
        //正常的main方法的事情都能做
        //只是在一个新的线程中进行
        System.out.println("This is a new thread!");
    }
}
