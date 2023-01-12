package com.java.intro.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //主线程
        //一个main 表示一个主线程
        //获取当前线程的信息
        Thread thread = Thread.currentThread();
        thread.setName("Main Thread");
        System.out.println(thread.getName());

        //睡眠（测试的时候用）
        //自己睡自己醒
//        Thread.sleep(2000);//睡眠2000ms
//        TimeUnit.SECONDS.sleep(2L);//使用时间，睡眠2秒

        //创建线程
//        createThreadTest();

        //提前制定的计划
//        futureTaskTest();

        //线程池
        //一般一个项目不同类型的线程池只有一个
        //不需要star
//        threadPoolTest1();
//        threadPoolTest2();

        //经典存取取钱 幻读
        //synchronized () 同步锁
        //ReentrantLock lock 显示锁
        //CAS TODO 乐观 悲观
//        bankTest();
//        casTest();
        //报锁睡觉
//        deadlockTest1();
//        deadlockTest2();

        //线程安全的类
//        threadSafeClass();

        //生产者消费者模式
        KFCTest();
    }

    private static void KFCTest() {
        KFC kfc = new KFC();
        //生产者
        Thread t1 =  new Thread(()->{
            while (true){
                try {
                    kfc.makeSandwich();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //消费者
        Thread t2 =  new Thread(()->{
            while (true){
                try {
                    kfc.eatSandwich();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t3 = new  Thread(()->{
            while (true){
                try {
                    kfc.eatSandwich();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t2.start();
        t1.start();
        t3.start();
    }

    private static void threadSafeClass() {
        //ArrayList是线程不安全的，Vector线程相对安全
        //其实底层都是数组，必然有 越界和null 的风险
//        List<Integer> list = Collections.synchronizedList(new ArrayList<>());//实际不用
        //HashMap是线程不安全的，在单线程项目中使用 没有任何问题
        //- Hashtable是线程安全的，但是性能比较低（给每个方法都加了synchronized，相当于锁住的是整个hash表 ）
        //- ==ConcurrentHashMap==性能相对较高（锁的粒度减小了，只锁住当前桶位的对象）在实际开发中使用频繁；
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();//实际使用
    }

    private static void deadlockTest2() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("Synchronized A 1");
                Thread.sleep(2000);
                lock2.lock();
                System.out.println("Synchronized A all 1->2");
                lock2.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
            }
        }).start();
        new Thread(() -> {
            lock2.lock();
            try {
                System.out.println("Synchronized B 1");
                Thread.sleep(2000);
                lock1.lock();
                System.out.println("Synchronized B all 2->1");
                lock1.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock2.unlock();
            }

        }).start();
    }

    private static void deadlockTest1() {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1) {
                try {
                    System.out.println("Synchronized A 1");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2) {
                    System.out.println("Synchronized A all 1->2");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (o2) {
                try {
                    System.out.println("Synchronized B 2");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o1) {
                    System.out.println("Synchronized B all 2->1");
                }
            }
        }).start();
    }

    private static void casTest() {
        //CAS是CPU的原子指令，CAS（Compare And Swap）比较并交换
        //优点是无锁编程，所以没有上锁和释放锁的性能损失，直接操作cpu指令性能高
        //缺点是 自旋 会一直 占用CPU资源，长时间得不到结果，会一直自旋下去
        //适合简单对象的操作、冲突较少的操作
        //TODO
    }

    private static void bankTest() throws InterruptedException {
        Bank bank = new Bank();
        new Thread(() -> {
            Thread.currentThread().setName("Save money.");
            for (int i = 0; i < 1000; i++) {
                bank.saveMoney(10);
            }
        }).start();
        new Thread(() -> {
            Thread.currentThread().setName("Withdraw money.");
            for (int i = 0; i < 1000; i++) {
                bank.withdrawMoney(1);
            }
        }).start();
        //等待不是每次都能生效
        //最好在操作数据的方法上添加synchronized-锁，否则上面的循环可能会交替进行
        TimeUnit.SECONDS.sleep(1L);
        synchronized (bank) {
            System.out.println("Balance: " + bank.getBalance());
        }
    }

    private static void threadPoolTest2() {
        //将线程池封装为工具类
        for (int i = 0; i < 20; i++) {
            //线程中不能直接使用变量，只能给一个final值
            final int num = i;
            ExecutorFactory.execute(() -> {
                //因为 异步性 输出的顺序 按照不可预知的方式发展
                System.out.println("数字：" + num);
                try {
                    //手动阻塞线程（模拟大的方法）
                    Thread.sleep(2000);
                    //用Fixed线程池，会导致池中所有的线程用完后，线程等待
                    //可以防止死循环
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void threadPoolTest1() {
        //【非正式使用场景】
        //固定数量的线程池
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        //可缓存的线程池 如果池子中没有空闲线程 此时会新建线程执行任务；内部线程个数无要求 会把系统资源耗尽 导致程序停止
//        ExecutorService executorService = Executors.newCachedThreadPool();
        //池子中只有单个线程 非常适合用来做串行化任务
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService1.submit(() -> {
            System.out.println("This is a thread in pool.");
        });
        executorService1.execute(() -> {
            System.out.println("This is a thread in pool.");
        });
        //executorService会一直等待，程序不会结束

        //定时或延期执行线程
        ScheduledExecutorService executorService2 = Executors.newScheduledThreadPool(3);
        executorService2.schedule(() -> {
            System.out.println("Hi!");
        }, 1, TimeUnit.SECONDS);
        executorService2.scheduleAtFixedRate(() -> {
            System.out.println("延迟1秒执行，每隔3秒执行一次");
        }, 1, 3, TimeUnit.SECONDS);
    }

    private static void futureTaskTest() throws InterruptedException, ExecutionException {
//        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return 1000;
//            }
//        });
        String s = "123";
        FutureTask<String> futureTask1 = new FutureTask<>(() -> s + "000");
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> Integer.valueOf(s));
        new Thread(futureTask2).start();//开启多线程并执行上面声明的计划
        //另一个线程与主线程“同时”运行，需要等待一段时间
        Thread.sleep(1000);//futureTask 运行结束后会把值存到 instance 中
        System.out.println(futureTask2.get());//获取futureTask中方法的返回值
    }

    private static void createThreadTest() {
        //创建并执行子线程
        //1.
        ThreadTest1 test1 = new ThreadTest1();
        test1.start();
        //2.
        Thread test2 = new Thread(new ThreadTest2());
        test2.start();
        //3.
        new Thread(new Runnable() {
            @Override
            public void run() {
                //不使用线程值的情况下
                //直接写下线程所要做的事
            }
        }).start();
        //4.
        new Thread(() -> {
            //不使用线程值的情况下
            //直接写下线程所要做的事
        }).start();
    }
}
