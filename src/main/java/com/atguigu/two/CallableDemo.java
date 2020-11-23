package com.atguigu.two;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @program: study
 * @description: callable 接口以及线程实例
 * 新建线程的三种方法
 * 1.new Thread();
 * 2.继承 Thread类重写run方法
 * 3.实现runnable接口 实现run方法
 * @author: guoxiaobing
 * @create: 2020-02-03 12:38
 */

class ThreadDemo1 implements Runnable{

    @Override
    public void run() {
        try {
        System.out.println("come in run ThreadDemo1.....");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {

            System.out.println(e);
        } finally {

        }
    }
}
class ThreadDemo2 extends Thread{
    @Override
    public void run() {
        super.run();
    }
}
class ThreadDemo3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

/**
 * Runnable 跟Callable 接口区别 三种
 * 1.实现的接口不一样 一个是runnable callable
 * 2.实现的方法是run方法 call方法
 * 3.call方法要抛出异常并且有返回值
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        /**
         * 一个是run方法 一个是call方法如何关联在一起那？
         *runnable接口中有一个接口是 RunnableFuture  实现类是 futureTask(Callable  callable)  FutureTask(Callable<V> callable)
         */


        ThreadDemo3 threadDemo3 = new ThreadDemo3();
        FutureTask<Integer> futureTask = new FutureTask(threadDemo3);
        Thread thread = new Thread(futureTask,"t1");
        thread.start();

        while (!futureTask.isDone()){
            System.out.println("还没有结束我在等待 等待。。");
        }
        Integer integer = futureTask.get();

        System.out.println(integer);
        /**
         * get方法会阻塞 会等待call接口执行完才会返回结果 所以在这里要将get放在最后
         * 为什么会有返回值 futureTask继承了RunnableFuture接口 RunnableFuture接口继承了runnable接口
         * 在thread.start(); 的时候会调用run方法 在run方法中会调用call方法 在call方法中执行结束后会set一个值
         * 然后get即可获取 如果get的时候还没有set 则会阻塞 。
         * 多个线程调用同一个futureTask 则只会执行一次
         */
    }
}