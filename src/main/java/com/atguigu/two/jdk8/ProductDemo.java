package com.atguigu.two.jdk8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 0311
 * @description: 线程一边生产 一边消费的案例
 * @author: guoxiaobing
 * @create: 2020-04-04 16:15
 */
class T {
    private int i = 0;

    public synchronized void add() {
        while (i != 0) {
            try {
                this.wait();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
            }
        }
        i++;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "  " + i);
    }

    public synchronized void sub() {
        while (i != 1) {
            try {
                this.wait();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
            }
        }
        i--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "  " + i);
    }
}

class Tlock {
    private int i = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            while (i != 0) {
                condition.await();
            }
            i++;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "  " + i);
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            lock.unlock();
        }

    }

    public void sub() {
        lock.lock();
        try {
            while (i != 1) {
                condition.await();
            }
            i--;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "  " + i);
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}

public class ProductDemo {
    public static void main(String[] args) {
        T t = new T();
        Tlock tlock = new Tlock();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                // t.add();
                tlock.add();
            }
        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                //  t.sub();
                tlock.sub();
            }
        }, "T2").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                // t.add();
                tlock.add();
            }
        }, "T3").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                //  t.sub();
                tlock.sub();
            }
        }, "T4").start();
    }
}