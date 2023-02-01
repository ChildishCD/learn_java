package com.javasm.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFactory {
    private ExecutorFactory(){}
    private static ExecutorService executorService;
    static {
        init(5);
    }

    public static void init(Integer num){
        if (num <=0 ){
            executorService = Executors.newCachedThreadPool();
        }else {
            executorService = Executors.newFixedThreadPool(num);
        }
    }
    //对外公开一个 可执行方法
    public static void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
