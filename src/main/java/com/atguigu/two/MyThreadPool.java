package com.atguigu.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: study
 * @description: 线程池 手写线程池
 * @author: guoxiaobing
 * @create: 2020-02-04 14:30
 *
 *
 * 线程池七大参数
 * coreppoolsize  线程池中常住线程数量
 * maximumpoolsize 线程池中最大线程数量
 * keepAliveTime  线程闲置销毁的时间 超过这个时间还没有任务则销毁
 * TimeUnit 时间单位
 * blockingQueue 阻塞队列 候客区
 * Threadfactory 线程工程 生产线程
 * RejectedExecutionHandler 拒绝策略
 *
 *
 * 线程池工作原理 比如一个线程池最大线程数是 5 常驻线程数是 2即corepoolSize
 * 1.请求来了先判断当前工作线程数是否大于corepoolsize 如果大于则进入候客区即blockingQueue
 * 2.判断BlockingQueue是否满了 如果满了则将当前线程数量调制 maximunpoolsize
 * 4.如果 阻塞队列满了 同时工作的线程也达到了最大的容量则执行拒绝策略 即 rejectedExecutionHandler
 * 5. 现在开始线程闲下来了，则在等待了keepalivetime后还没有收到请求则会销毁其他的线程 线程池中保留常驻线程
 */
public class MyThreadPool {
    public static void main(String[] args) {
        System.out.println("当前线程的cpu核数是：" + Runtime.getRuntime().availableProcessors());
        //executor  executors

        ExecutorService service = Executors.newFixedThreadPool(5); //固定长度的线程池 底层调用的是阻塞队列 LingkedBlockingQueue
        // ExecutorService service = Executors.newSingleThreadExecutor(); //一个线程池只有一个线程  底层也是LingkedBlockingQueue
        //ExecutorService service = Executors.newCachedThreadPool(); //带有缓存的线程池 具体用几个或者怎么样处理 不用我们管具体一池多少个线程不用管  底层是 SynchronousQueue


        //10个人来银行办业务 只有5个银行职员

        try {
            for (int i = 1; i < 11; i++) {
                //执行任务
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "来办理业务了。。。。");
                });

                TimeUnit.MILLISECONDS.sleep(20);
            }
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            //关闭
            service.shutdown();
        }

    }
}