package com.atguigu.two.jdk8;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @program: 0311
 * @description: 带返回值的线程类
 * @author: guoxiaobing
 * @create: 2020-04-04 17:04
 */
public class ThreadReturn   {
    public static void main(String[] args) throws Exception {


        Callable<Integer> callable = ()->{
            TimeUnit.SECONDS.sleep(2);
            return 1;
        };
        FutureTask futureTask = new FutureTask(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
        System.out.println("123");
       // Thread thread1 = new Thread(c);
    }



}