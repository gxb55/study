package com.atguigu.two.jdk8;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: 0311
 * @description: SemaPhore实例
 * @author: guoxiaobing
 * @create: 2020-04-09 14:53
 * *
 * * 在信号量上我们定义两种操作：
 * * acquire（获取） 当一个线程调用 acquire 操作时，它要么通过成功获取信号量（信号量减 1），
 * * 要么一直等下去，直到有线程释放信号量，或超时。
 * * release（释放）实际上会将信号量的值加 1，然后唤醒等待的线程。
 * *
 * * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 * <p>
 * 六辆汽车停三个车位 模拟
 */
public class SemaPhoreDemo {
    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        semaphore.release();

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    TimeUnit.MILLISECONDS.sleep(1500);
                    System.out.println(Thread.currentThread().getName()+"走了哦！");
                } catch (Exception e) {

                    System.out.println(e);
                } finally {
                    semaphore.release();
                }

            }, "T" + i).start();
        }
    }
}