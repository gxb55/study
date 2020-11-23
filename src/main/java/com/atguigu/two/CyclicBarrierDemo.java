package com.atguigu.two;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

/** cyclicbarrier
 * @program: study 循环屏障 cyclic barrier
 * @description: 做加法 比如办公室等到人齐了就开会做加法   cyclicBarrier
 * @author: guoxiaobing
 * @create: 2020-01-31 14:05
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        List list1 = new CopyOnWriteArrayList();
        List list2 = Collections.synchronizedList(list);
        CyclicBarrier cb = new CyclicBarrier(7, () -> {
            System.out.println("办公室的七个人来齐了，大家开会。");
        });

        for (int i = 1; i < 8; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    System.out.println("第" + temp + "人来了，他在等待。。");
                    cb.await();
                }catch (Exception e){

                }
            }, String.valueOf(i)).start();
        }
    }
}