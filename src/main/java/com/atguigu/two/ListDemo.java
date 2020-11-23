package com.atguigu.two;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ArrayList 多线程中会产生 java.util.ConcurrentModificationException的异常解决方法
 * 1.vector jdk1.0提供 add方法加锁的 arrayList是1.2的所有理论上不要使用vector来代替 ArrayList
 * 2.Collections.synchronizedList( new ArrayList<>())
 * 3.CopyOnWriteArrayList
 * 以上保证了多线程中不出错
 *
 */
public class ListDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        mapNoSafe();
    }

    private static void cas() {
        mapNoSafe();
        AtomicInteger integer = new AtomicInteger();
        integer.getAndIncrement();
        integer.compareAndSet(100,101);
    }

    private static void mapNoSafe() {
        // setNoSafe();
           Map map = new HashMap();
        Map map1  = new ConcurrentHashMap();
        Map map2 = Collections.synchronizedMap(new HashMap<>());
        Map map3 = new Hashtable();
        Map map4 = new TreeMap();
        for (int i = 0; i < 500; i++) {
            final int stmp = i;
            new Thread(() -> {
                map.put(stmp + "", stmp);
                System.out.println(map.toString());
            }, String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        // listNoSafe();
        //list map set 都是是实现了Collection 接口所以都是线程不安全的 在解决的时候第一是CollectionS工具类提供了一个线程安全的方法
        //第二就是juc包里提供了线程安全的对象用的是  ReentrantLock lock = this.lock;
        //CopyOnWriteArraySet CopyOnWriteArrayList ConcurrentHashMap 分别是juc提供的线程安全的

        Set<Integer> set = new HashSet();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                set.add((int)(Math.random() * 10));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        Set treeSet = new TreeSet();
        new HashMap<>();
        new Hashtable<>();
        /**
         * treeSet 里面用的是TreeMap
         * HashSet 里面是HashMap;
         * 其中set是一个值map是key value 那就是value都是一个常量
          */}

    private static void listNoSafe() {
        System.out.println("begin-- 20200127 11：30");
        List list = new ArrayList<Object>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        System.out.println(list.toString() + "单线程下面list的add方法没有任何问题 下面演示多线程下面的 list的add方法 将会抛出异常 java.util.ConcurrentModificationException");

        List listException = new ArrayList<Integer>();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                listException.add(Math.random() * 10);
                System.out.println(listException);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException java.util.ConcurrentModificationException
        //产生的原因是 list的 add方法没有添加所 vector

        Vector v = new Vector();
        v.add(45);

        List li = Collections.synchronizedList( new ArrayList<>());
        List list1 = new CopyOnWriteArrayList();
    }
}
