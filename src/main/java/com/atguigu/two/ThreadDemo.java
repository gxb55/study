package com.atguigu.two;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: study
 * @description: 线程面试题demo  多个线程交替打印 线程有虚假唤醒的情况判断的时候要用while
 * @author: guoxiaobing
 * @create: 2020-02-02 10:10
 */

class demoData {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile Map<String, Object> map = new HashMap();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    /**
     * ReentrantReadWriteLock 读写锁  读锁共享锁 写锁独占锁 性能肯定比reentrantLock性能好  独占锁
     * 自旋锁
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {

        try {
            rwlock.writeLock().lock();
            Thread.sleep(600);
            map.put(key, value);
            System.out.println("存储成功。。key:" + key + "value:" + value);
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            rwlock.writeLock().unlock();
        }

    }

    public void get(String key) {

        try {
            rwlock.readLock().lock();
            Object o = map.get(key);
            Thread.sleep(600);
            System.out.println("取值成功。。key:" + key + "value:" + o);
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            rwlock.readLock().unlock();
        }

    }

    /**
     * 可以用 condition 也可以用    synchronized
     */

    /**
     * 两个线程交替打印aa bb
     * 两个线程一个将一个数加一 一个将一个数减一
     */

    public synchronized void print(String msg, int num, int f) {
        try {
            while (flag != num) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + ":" + msg);
            num = f;
            flag = num;
            this.notifyAll();
        } catch (Exception e) {

        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {

        demoData demoData = new demoData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demoData.print("AAA", 1, 2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demoData.print("BBB", 2, 1);
            }
        }, "t2").start();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                demoData.put(temp + "", temp);
            }, "t3").start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                demoData.get(temp + "");
            }, "t4").start();
        }
    }
}