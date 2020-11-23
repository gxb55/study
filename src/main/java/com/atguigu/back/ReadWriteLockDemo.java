package com.atguigu.back;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: 0311
 * @description: 读写锁  一个线程写入,100 个线程读取
 * @author: guoxiaobing
 * @create: 2020-04-08 16:23
 */
class MyQueue {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private volatile List<String> list = new ArrayList();

    public void write(String str) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            list.add(str);
            //TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void read() {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(list + " --" + Thread.currentThread().getName());
           // TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                myQueue.write(String.valueOf((int)(Math.random()*100)));
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                myQueue.read();
            }, "T" + i).start();
        }

    }
}