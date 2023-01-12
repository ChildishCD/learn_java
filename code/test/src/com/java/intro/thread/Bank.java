package com.java.intro.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private static boolean broken;

    static {
        broken = false;
    }

    private Double balance = 0.0;

    private final ReentrantLock lock = new ReentrantLock();

    public Bank() {
    }

    //-->同步锁
    public void saveMoney(Integer money) {
        //synchronized 应用于指定对象
        synchronized (this) {
            balance += Double.valueOf(money);
        }
    }

    //synchronized 应用于方法
    //非静态方法 锁实例
    //静态方法锁Class
    public synchronized void withdrawMoney(Integer money) {
        balance -= Double.valueOf(money);
    }

    //-->显示锁
    //PV操作
    public void saveMoney(Double money){
        lock.lock();
        try{
            balance += money;
        }finally {
            lock.unlock();
        }
    }

    public void withdrawMoney(Double money){
        lock.lock();
        try{
            balance -= money;
        }finally {
            lock.unlock();
        }
    }

    //可以锁套锁
    public synchronized static void brokenBank() {
        synchronized (Bank.class) {
            broken = true;
        }
    }

    public Double getBalance() {
        return balance;
    }
}
