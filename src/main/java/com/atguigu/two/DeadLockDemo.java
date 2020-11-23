package com.atguigu.two;

import java.util.concurrent.TimeUnit;

/**
 * @program: study
 * @description: 死锁的案例 手动写一个demo来实现死锁代码的演示
 * @author: guoxiaobing
 * @create: 2020-02-08 11:53
 */

class ThreadResource implements Runnable{
    private String lockA;
    private String lockB;

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println("获得锁"+lockA+" 尝试获取"+lockB);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (Exception e) {

                System.out.println(e);
            } finally {

            }
            synchronized (lockB){
                System.out.println("获得锁"+lockB+" 尝试获取"+lockA);
            }
        }
    }

    public ThreadResource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        System.out.println(System.getProperties());
        String lockA = "lockA";
        String lockB = "lockB";
        ThreadResource resource = new ThreadResource(lockA,lockB);
        ThreadResource resource1 = new ThreadResource(lockB,lockA);

        Thread thread = new Thread(resource);
        Thread thread1 = new Thread(resource1);
        thread.start();
        thread1.start();

        System.out.println("死锁了吧。。。。");

    }
}