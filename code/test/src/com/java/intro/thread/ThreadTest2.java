package com.java.intro.thread;

public class ThreadTest2 implements Runnable{
    @Override
    public void run() {
        System.out.println("This is a new thread!");
    }
}
