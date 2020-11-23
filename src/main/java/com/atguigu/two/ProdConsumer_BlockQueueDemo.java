package com.atguigu.two;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: study
 * @description: 生产者 消费者 阻塞队列版本  一个生产一个消费 多线程判断要用while 不然会虚假唤醒线程 spinLock
 * @author: guoxiaobing
 * @create: 2020-02-02 12:25
 */

class myResources {
    //线程操作资源类 这个属于资源类 阻塞队列为什么会线程安全是因为他的源码中加入了lock锁 在存取的时候都是锁着的所以安全 多线程不会出问题
    private volatile Boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public myResources(BlockingQueue blockingQueue) {
        System.out.println(blockingQueue.getClass().getName());
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws Exception {
        String data = null;
        boolean offer;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            offer = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName() + "存储成功。。存储的值是：" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + "存储失败。。存储的值是：" + data);
            }
            TimeUnit.MILLISECONDS.sleep(1500);
        }
        System.out.println(Thread.currentThread().getName() + " 大老板叫停了不再生产了");
    }

    public void consumer() throws Exception {
        String poll = null;
        while (flag) {
            poll = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if (null == poll || poll.equalsIgnoreCase("")) {
                System.out.println(Thread.currentThread().getName() + "消费失败 超时未取到内容。");
                flag = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费成功 取到的值是：" + poll);

            System.out.println();
            System.out.println();
            System.out.println();

        }
    }

    public void stop() {
        this.flag = false;
    }

}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        myResources myResources = new myResources(blockingQueue);

        new Thread(() -> {
            try {
                myResources.prod();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                myResources.consumer();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
            }
        }, "t2").start();


        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }

        myResources.stop();

        System.out.println("朋友们五秒时间到了 该停止了。。");
    }
}