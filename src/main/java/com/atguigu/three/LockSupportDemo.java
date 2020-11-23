package com.atguigu.three;

import org.springframework.boot.json.JacksonJsonParser;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 2
 * @description: lockSupport
 * @author: guoxiaobing
 * @create: 2020-11-15 11:06
 *
 * wait 方法会主动放弃锁
 * condition.await();方法会主动放弃锁
 *
 * sync和lock要成对出现
 * 且这些唤醒和放弃操作必须在sync和lock中才可以
 *
 * park unpark 阻塞和非阻塞 用的其实就是0跟1，发放的凭证最多的也是1
 * 默认是1， park之后变成了 0，需要unpark发放凭证才能正常运行
 */
public class LockSupportDemo {
    static Object object = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread T1 = new Thread(() -> {
        System.out.println("T1进来了");
            LockSupport.park();
        System.out.println("T1收到通知了");
        }, "T1");
        T1.start();

        Thread.sleep(3000);
        Thread T2 = new Thread(() -> {
            System.out.println("T2收到通知了");
            LockSupport.unpark(T1);
        }, "T2");
        T2.start();
    }

    private static void lockAwaitSignal() {
        new Thread(()->{
            System.out.println("T1获取锁了");
            lock.lock();
            try {
                condition.await();
                System.out.println("T1收到通知了");
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        },"T1").start();

        new Thread(()->{
            System.out.println("T2获取锁了");
            lock.lock();
            try {
                System.out.println("T2通知了");
                condition.signal();

            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        },"T2").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(()->{
            synchronized (object){
                System.out.println("T1获得锁了");
                System.out.println("T1主动放弃锁了");
                try {
                    object.wait();
                    System.out.println("T1收到通知了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(()->{
            synchronized (object){
                System.out.println("T2通知了");
                object.notify();
            }
        },"T2").start();
    }
}