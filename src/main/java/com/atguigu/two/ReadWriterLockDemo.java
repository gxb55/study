package com.atguigu.two;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: study
 * @description: 读写分离锁 读锁共享锁 写锁独占锁
 * 为何这个HashMap 多线程下面多次写入不会出错 是因为他加了volatile保证了原子可见性 如果不加则会报错 java.util.ConcurrentModificationException
 * @author: guoxiaobing
 * @create: 2020-01-30 12:35
 */
public class ReadWriterLockDemo {
    public volatile Map<String, Object> map = new HashMap();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String s, Object o) {
        try {
            lock.writeLock().lock();
            System.out.println("我准备开始写了。。。" + s);
            Thread.sleep(900);
            map.put(s, o);
            System.out.println("我写完了。。。" + s);
        } catch (Exception e) {

        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        try {
            lock.readLock().lock();
            System.out.println("我开始读了" + key);
            Object o = map.get(key);
            System.out.println("我读完了，读的结果是" + o);
            Thread.sleep(500);
        } catch (Exception e) {

        }finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriterLockDemo rwl = new ReadWriterLockDemo();
        for (int i = 0; i < 5; i++) {
            final int stmp = i;
            new Thread(() -> {
                rwl.put(stmp + "", stmp);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int stmp = i;
            new Thread(() -> {
                rwl.get(stmp + "");
            }, String.valueOf(i)).start();
        }

    }
}












