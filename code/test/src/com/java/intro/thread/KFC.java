package com.java.intro.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class KFC {
//    private ReentrantLock lock = new ReentrantLock();

    //货架 汉堡
    //可以是任何资源（数据库，进程...）
    private List<String> store = Collections.synchronizedList(new ArrayList<>());
    private static final Integer STORE_MAX = 5;

    //后厨
    public void makeSandwich() throws InterruptedException {
        synchronized (this) {
            while (store.size() == STORE_MAX) {//唤醒之后会再检查一遍
                System.out.println("Store full!!");
                this.wait();//货架满了就 释放锁 并等待
            }
//            lock.lock();
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int num = random.nextInt(0, 300);
            store.add("Sandwich" + num);
            System.out.println("Ding!");
            Thread.sleep(1000);//生产和消费的时间不相等
            this.notifyAll();
//            lock.unlock();
        }
    }

    //就餐区
    public void eatSandwich() throws InterruptedException {
        synchronized (this){
            while (store.size() == 0){
                System.out.println("Store empty!!");
                this.wait();
            }
//            lock.lock();
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int index = random.nextInt(store.size());
            store.remove(index);
            System.out.println("Yummy!");
            Thread.sleep(1000);
            this.notifyAll();
//            lock.unlock();
        }
    }
}
