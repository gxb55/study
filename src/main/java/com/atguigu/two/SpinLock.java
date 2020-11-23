package com.atguigu.two;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: study
 * @description: 自旋锁 学习b站视频 自旋不用阻塞但是会多次询问 如果一个线程一直占用资源不释放，那么自旋会造成cpu使用率升高 具体使用视情况而定
 * @author: guoxiaobing
 * @create: 2020-01-28 12:50
 */
public class SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        new Thread(()->{
            spinLock.myloCk();
            try {
                Thread.sleep(5);
            }catch (Exception e){

            }
            spinLock.myUnLock();
        },"t1").start();

        try {
            Thread.sleep(1);
        }catch (Exception e){

        }

        new Thread(()->{
            spinLock.myloCk();
            try {
                Thread.sleep(1);
            }catch (Exception e){

            }
            spinLock.myUnLock();
        },"t2").start();
    }

    public void myloCk(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"  进来了！");
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"  出去了！");
    }
}