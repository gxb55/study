package com.atguigu.two;

import java.util.concurrent.*;

/**
 * @program: study
 * @description: 手写线程池 以及拒绝策略代码实现
 * @author: guoxiaobing
 * @create: 2020-02-06 15:08
 * <p>
 * 线程池七大参数 以及 拒绝策略
 * 在面试中问你常用的是哪一个 正确答案是那个也不用一般都是手写的 因为java提供的队列默认值是integer.max_value 太大了容易内存溢出
 * <p>
 * 拒绝策略 线程池最大的容量就是线程池大小加上阻塞队列的大小
 * 1.AbortPolicy 一旦需要的线程超过了线程池的最大容量则直接报错  java.util.concurrent.RejectedExecutionException
 * 2.CallerRunPolicy 超过线程池最大容量的则直接 回退 从哪来的还去哪 这边是main线程启动的还是要回到main线程中去
 * 3.discardOldestpolicy 抛弃等待时间最长的线程
 * 4.discatdpolicy 直接抛弃多余的线程
 *
 * 问题 线程池大小如何设置？
 * 1.如果是计算的比较多就是没有io切换和阻塞的 则是电脑核数加1  CPU密集型
 * 2.如果涉及到io频繁的切换和阻塞则是 电脑核数*2           IO密集型
 * 3.密集系数 电脑核数/1-密集系数  密集系数通常是 0.8-0.9 之间
 */
public class MyThreadPool1 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());


        ExecutorService service = Executors.newFixedThreadPool(5);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());


        try {
            for (int i = 1; i <= 10; i++) {
                final int temp = i ;
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "来了。。"+temp);
                });
            }

        } catch (Exception e) {

            System.out.println(e);
        } finally {
            threadPoolExecutor.shutdown();
        }


    }
}