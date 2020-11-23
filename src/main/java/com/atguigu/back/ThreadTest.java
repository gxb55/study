package com.atguigu.back;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 0311
 * @description: 线程测试 根据之前的学习现在来测试一把，顺便复习之前的知识
 * @author: guoxiaobing
 * @create: 2020-03-21 20:12
 */
class T {
    public int a = 1;
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void add() {
        try {
            lock.lock();
            while (a != 1) {
                condition.await();
            }
            System.out.println(a--);
            condition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void sub() {
        try {
            lock.lock();
            while (a != 0) {
                condition.await();
            }
            System.out.println(a++);
            condition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public synchronized void change(String msg, int b, int c) throws Exception {
        while (a != b) {
            this.wait();
        }
        System.out.println(a);
        a = c;
        this.notifyAll();
    }
}

class Resource {
    public AtomicInteger i = new AtomicInteger(0);
    public volatile Boolean flag = true;
    public BlockingQueue blockingQueue = null;

    public Resource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws Exception {
        boolean x;
        while (flag) {
            int a = this.i.incrementAndGet();
            x = blockingQueue.offer(i, 2, TimeUnit.SECONDS);
            if (x) {
                System.out.println("成功生产了一个: " + i);
            } else {
                System.out.println("失败生产了一个");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("消费者没有了 我也不需要生产了");
    }

    public void cons() throws Exception {
        while (flag) {
            Object poll = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (poll != null) {
                System.out.println("我消费成功了:" + poll);
            } else {
                System.out.println("朋友们 我被叫停了。");
                flag = false;
            }
        }
    }

    public void stop() {
        this.flag = false;
    }
}

public class ThreadTest {
    public static void main(String[] args) throws Exception {
        /**
         * synchronized版本的
         * 1多个线程的话会唤醒多个颗粒度不够细
         */
        // syncType();
        /**
         * lock的模式
         */
        //lockType();
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        Resource resource = new Resource(blockingQueue);
        new Thread(() -> {
            try {
                resource.prod();
            } catch (Exception e) {

                System.out.println(e);
            } finally {

            }
        }, "T1").start();

        new Thread(() -> {
            try {
                resource.cons();
            } catch (Exception e) {

                System.out.println(e);
            } finally {

            }
        }, "T2").start();
        Thread.sleep(10000);
        resource.stop();


        System.out.println("大家都停手吧 别干了");
    }

    private static void lockType() {
        T t = new T();
        new Thread(() -> {
            for (int j = 0; j < 20; j++) {
                t.add();
            }
        }, "T1").start();

        new Thread(() -> {
            for (int j = 0; j < 20; j++) {
                t.sub();
            }
        }, "T2").start();
    }

    private static void syncType() {
        T t = new T();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    t.change("AAA", 1, 2);
                } catch (Exception e) {

                }

            }
        }, "T2");
        t2.start();

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    t.change("BBB", 2, 1);
                } catch (Exception e) {

                }
            }
        }, "T3");
        t3.start();
    }
}