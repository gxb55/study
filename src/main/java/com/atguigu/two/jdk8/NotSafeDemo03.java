package com.atguigu.two.jdk8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 0311
 * @description: List 对象
 * @author: guoxiaobing
 * @create: 2020-04-03 19:41
 */
public class NotSafeDemo03 {
    public static void main(String[] args) {
        /**
         * ArrayList接口
         * 1.底层是什么？ 是数组
         * 2.什么数组 ？ object类型数组
         * 3.默认多长？10
         * 4.扩容怎么算？ 每次增加原来的一般 比如是10-》15-》22
         * 5.数组是怎么扩容的 ？ Array.copy
         *
         */
        Lock lock = new ReentrantLock();
        List list = new ArrayList();
        list.add(1);

        for (int i = 0; i < 50; i++) {
            list.add(i);
        }

        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                list.add(Math.random() * 500);
                try {
                    Thread.sleep(50);
                } catch (Exception e) {

                    System.out.println(e);
                } finally {

                }
            }, "t1").start();
        }

        Vector<String> vector = new Vector<>();
        List list1 =  Collections.synchronizedList(new ArrayList<String>());
        List list2 = new CopyOnWriteArrayList();
        System.out.println(list);
        /**
         * 1.Vector 不推荐
         * 2.CopyOnWriteArrayList 线程安全
         * 3.Collections.synchronizedList(new ArrayList<String>())
         */

        Set set = new HashSet();

        Map map = new ConcurrentHashMap();
    }
}