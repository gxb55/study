package com.atguigu.two;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: study
 * @description: 生产者消费者 demo 线程操作资源类
 * @author: guoxiaobing
 * @create: 2020-02-01 14:41
 *
 * 问题1.sync和lock对比差异
 * 1.sync是jvm提供的关键字 lock是jdk1.5以后提供的接口
 * 2.都是非公平锁 lock可以提供为公平的默认是非公平锁
 * 3.sync不可中断 在lock提供了trylock方法可以在某个时间内获得锁也可以中断
 * 4.sync代码块可以自动解锁 而sync则需要在try和catch中手动上锁和解锁 相对来讲颗粒度更细
 * 5.lock提供condition方法可以精确的唤醒某个进程 而sync则是随机唤醒一个或者全部唤醒
 */
public class ProdConsumer_TraditionDemo {
    public int temp = 0;

    public static void main(String[] args) {

       /* new CountDownLatch();
        new CyclicBarrier();*/
     /*   Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        semaphore.release();*/
        shareData shareData = new shareData();
       new Thread(() -> {
            for(int i=0;i<5;i++){
                shareData.increment();
            }

        }, "t1").start();

        new Thread(() -> {
            for(int i=0;i<5;i++){
                shareData.decrement();
            }
        }, "t2").start();


        System.out.println("=============================");
        new Thread(() -> {
            for(int i=0;i<5;i++){
                shareData.oldIncrement();
            }

        }, "t3").start();

        new Thread(() -> {
            for(int i=0;i<5;i++){
                shareData.oldDecrement();;
            }
        }, "t4").start();
    }

}

class shareData {
    private int temp = 0;
    private int old = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        try {
            lock.lock();
            while (temp != 0) {
                condition.await();
            }
            temp++;
            System.out.println("我是增加方法，增加后是："+Thread.currentThread().getName()+"线程"  + temp);
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        try {
            lock.lock();
            while (temp != 1) {
                condition.await();
            }
            temp--;
            System.out.println("我是减少方法，减少后是："+Thread.currentThread().getName()+"线程" + temp);
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public synchronized void oldIncrement() {
        try {
            while (old != 0) {
               this.wait();
            }
            old++;
            System.out.println("我是old增加方法，增加后是："+Thread.currentThread().getName()+"线程"  + old);
            this.notifyAll();
        } catch (Exception e) {

        } finally {
        }
    }

    public  synchronized void oldDecrement() {
        try {
            while (old != 1) {
                this.wait();
            }
            old--;
            this.notifyAll();
            System.out.println("我是old减少方法，减少后是："+Thread.currentThread().getName()+"线程" + old);
        } catch (Exception e) {

        } finally {
        }
    }
}