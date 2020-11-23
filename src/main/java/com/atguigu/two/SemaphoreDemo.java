package com.atguigu.two;

import java.util.concurrent.Semaphore;

/**
 * @program: study
 * @description: 信号灯 信号量 跟countdown latch和cyclicBarrier相比 既可以加也可以减 多个线程抢占多个资源可以用seamphore
 * @author: guoxiaobing  六辆车抢占三个车位的情况
 * @create: 2020-01-31 14:26
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        /**
         * 这里的false也是非公平锁跟lock和sync一样的 当permits为1的时候就跟lock跟sync一样了 多个线程抢占多个资源 资源使用完要减 reselaes
         */
        Semaphore semaphore = new Semaphore(3,false);

        for(int i=1;i<7;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢占到了车位。。");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"释放了车位。。");
                    semaphore.release();
                }catch (Exception e){

                }
            },String.valueOf(i)).start();
        }
    }
}