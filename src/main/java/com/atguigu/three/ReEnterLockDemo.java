package com.atguigu.three;

/**
 * @program: 2
 * @description: 可重入锁 lock显式可重入锁，sync隐式可重入锁
 * @author: guoxiaobing
 * @create: 2020-11-15 10:43
 *
 * 每一个锁对象拥有一个锁计数器，和一个指向持有改锁线程的指针
 *
 * sync字节码都是monitorenter monintorexit，进入和出去
 */
public class ReEnterLockDemo {
    public  static Object object = new Object();
    public static void main(String[] args) {
        m1();
        m1();
    }
    public static void m1(){
        new Thread(()->{
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+"外层-------------");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName()+"中层-------------");
                    synchronized (object){
                        System.out.println(Thread.currentThread().getName()+"内层-------------");
                    }
                }
            }
        },"T1").start();
    }

}