package com.atguigu.two.jdk8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 0311
 * @description: 三个售票员 卖出30张票的 demo
 * @author: guoxiaobing
 * @create: 2020-04-03 16:17
 */
class saleTick {
    private int tickNum = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if(tickNum>0){
                System.out.println("线程:  " + Thread.currentThread().getName() + "  消费了:" + tickNum-- + " 剩余票数：" + tickNum);
            }
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo01 {
    private static AtomicInteger integer = new AtomicInteger(30);
    private static volatile Boolean flag = true;

    public static void main(String[] args) {

        // Thread.State
        /**
         * 第一种
         */
       /* for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (flag) {
                    try {
                        saleTick();
                      //  Thread.sleep(500);
                    } catch (Exception e) {

                        System.out.println(e);
                    } finally {

                    }
                }
            }, "T" + i + "").start();
        }*/
        /**
         * 第二种
         */
        saleTick saleTick = new saleTick();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                for(int j=0;j<40;j++){
                    saleTick.sale();
                }
            },"t"+i).start();
        }

        ExecutorService service = Executors.newFixedThreadPool(5);
        // LinkedBlockingDeque();

    }

    public synchronized static void saleTick() {
        if (integer.get() > 0) {
            integer.decrementAndGet();
            System.out.println("我是第几张票" + String.valueOf(integer.get()) + "  " + Thread.currentThread().getName());
        } else {
            flag = false;
        }
    }
}