package com.atguigu.back;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 0311
 * @description: 循环屏障 满足条件则不再阻塞
 * @author: guoxiaobing
 * @create: 2020-04-08 16:57
 * CyclicBarrier
 * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有
 * 被屏障拦截的线程才会继续干活。
 * 线程进入屏障通过 CyclicBarrier 的 await()方法。
 * <p>
 * 集齐 7 颗龙珠就可以召唤神龙
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("你妹的 我终于集齐龙珠了无情");
        });
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("牛逼啊 我现在收集到了第" + Thread.currentThread().getName() + "颗龙珠了");
                try {
                    int await = cyclicBarrier.await();
                    //System.out.println(await);
                } catch (Exception e) {

                    System.out.println(e);
                } finally {

                }
            }, "t" + i).start();

            Lock lock = new ReentrantLock();

        }
    }
}