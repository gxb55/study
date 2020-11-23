package com.atguigu.two;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reenCock  synchronized 典型的可重入锁（递归锁）
 */
public class ReenterCockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSMS();
        },"t1").start();

        new Thread(()->{
            phone.sendSMS();
        },"t2").start();


        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        System.out.println("reentranlock 也是一样的。。。");

        Thread thread1 = new Thread(phone,"t1");
        Thread thread2 = new Thread(phone,"t2");
        thread1.start();
        thread2.start();
    }
}
class Phone implements Runnable{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getId()+" sendSMS ");
        sendEamil();
    }

    public synchronized void sendEamil(){
        System.out.println(Thread.currentThread().getId()+" sendEamil ");

    }


    @Override
    public void run() {
        set();
    }
    Lock lock = new ReentrantLock();
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" set ....");
            get();
        }finally {
         lock.unlock();
        }
    }

    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" get************* ");
        }finally {
            lock.unlock();
        }
    }
}
