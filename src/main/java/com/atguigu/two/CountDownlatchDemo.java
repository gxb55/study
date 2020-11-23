package com.atguigu.two;

import java.util.concurrent.CountDownLatch;

/**
 * @program: study
 * @description: countDownLatch 计数器的效果
 * @author: guoxiaobing
 * @create: 2020-01-30 15:01
 */
public class CountDownlatchDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(6);
    public static void main(String[] args) throws Exception{

        for(int i=1;i<7;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开了教室。。");
                countDownLatch.countDown();
            },CountryEnum.getCountry(i).getRetMsg()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"人都走完了班长准备锁门。。");

    }
}