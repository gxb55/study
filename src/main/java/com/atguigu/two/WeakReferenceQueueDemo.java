package com.atguigu.two;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @program: lvmama1
 * @description: 引用队列 虚引用或者弱引用在被gc后会被放到 referenceQueue中
 * @author: guoxiaobing
 * @create: 2020-03-03 21:14
 */
public class WeakReferenceQueueDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object>  weakReference = new WeakReference<Object>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}