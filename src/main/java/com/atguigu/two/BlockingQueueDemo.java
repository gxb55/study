package com.atguigu.two;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: study
 * @description: 阻塞队列 BLockQueue； list跟queue属于同一级别的同样的实现了collection接口 list的实现类有 ArrayList LinkedList CopyOnWriteArrayList等
 * 同样的queue也提供了很多实现类 这里我们仅研究三种即 队列都是先进先出的
 * ArrayBlockingQueue 有界阻塞队列
 * LinkedBlockingQueue 基于链表的有界阻塞队列 new出来的时候长度就是Integer.MAX_VALUE 21亿
 * SynchronousQueue 只有存取一个元素 消费完新的元素才可以存储进去
 * 常用的api
 * @author: guoxiaobing
 * @create: 2020-01-31 15:51
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        BlockingQueue blockingQueue = new SynchronousQueue();
        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue(3);
        BlockingQueue<String> blockingQueue2 = new LinkedBlockingQueue<>(3);

        /**
         * 下面用blockingQueue 来掩饰常用的存储api
         * 第一组   blockQueueAddRemove(blockingQueue1); 存 add;取 remove;探测元素是 element 返回队列的首个元素 如果队列的大小是三 那么往里面存第四个的时候会报错 存取都会抛异常 Queue full   取异常  java.util.NoSuchElementException
         * 第二组     blockQueueOfferPoll(blockingQueue1); 存offer 取poll 探测peek  不报错存的时候会返回true或者false 取的时候如果没有则会返回null
         * 第三组 blockingQueuePutTake(blockingQueue1); 存 put 取take 会阻塞 比如队列大小是三 当存入第四个元素的时候会阻塞 一直阻塞到里面取出一个元素然后第四个元素如堆才可以 取的时候也是一样 存取都会阻塞
         * 第四组  blockingQueueTimeUnit(blockingQueue1); 存 offer 取poll 带时间戳的 阻塞过这个时间后如果没有存进去或者没有取到则结束
         *
         */
       /* try{
            CountDownLatch countDownLatch = new CountDownLatch(5);//做减法 秦灭六国一个一个国家灭 灭完后 走主线程
            countDownLatch.countDown();//灭一个执行一次
            countDownLatch.await();//主线程等待 走到await会判断灭了几个国家 一直到灭完才能往下走

            CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
                System.out.println("人到齐了开会吧 一共五个人开会。。");
            });
            cyclicBarrier.await();//执行一次数加一 加到5的时候执行构造方法中的东西 构造方法中要传入一个线程
        }catch (Exception e){

        }*/



        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 7; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("我是" + temp + "号车，我进来了。。");
                    Thread.sleep(3000);
                } catch (Exception E) {

                }finally {
                    System.out.println("我是" + temp + "号车，我走了后面的车你们可以进来了");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }

    private static void blockingQueueTimeUnit(BlockingQueue<String> blockingQueue1) {
        try {
            blockingQueue1.offer("a", 2, TimeUnit.SECONDS);
            blockingQueue1.offer("b", 2, TimeUnit.SECONDS);
            blockingQueue1.offer("c", 2, TimeUnit.SECONDS);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            blockingQueue1.offer("d", 2, TimeUnit.SECONDS);


            System.out.println(blockingQueue1.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue1.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue1.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue1.poll(2, TimeUnit.SECONDS));
        } catch (Exception e) {

        }
    }

    private static void blockingQueuePutTake(BlockingQueue<String> blockingQueue1) {
        try {
            blockingQueue1.put("a");
            blockingQueue1.put("b");
            blockingQueue1.put("c");

            System.out.println(blockingQueue1.take());
            System.out.println(blockingQueue1.take());
            System.out.println(blockingQueue1.take());
            System.out.println(blockingQueue1.take());

        } catch (Exception e) {

        }
    }

    private static void blockQueueOfferPoll(BlockingQueue<String> blockingQueue1) {
        System.out.println(blockingQueue1.offer("a"));
        System.out.println(blockingQueue1.offer("b"));
        System.out.println(blockingQueue1.offer("c"));
        System.out.println(blockingQueue1.offer("c"));

        System.out.println(blockingQueue1.peek());

        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
    }

    private static void blockQueueAddRemove(BlockingQueue<String> blockingQueue1) {
        System.out.println(blockingQueue1.add("a"));
        System.out.println(blockingQueue1.add("b"));
        System.out.println(blockingQueue1.add("c"));

        System.out.println(blockingQueue1.element());

        System.out.println(blockingQueue1.remove());
        System.out.println(blockingQueue1.remove());
        System.out.println(blockingQueue1.remove());
    }
}