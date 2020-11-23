package com.atguigu.two;

import java.lang.ref.WeakReference;

/**
 * @program: lvmama1
 * @description: 弱引用 只要有gc都回收
 * @author: guoxiaobing
 * @create: 2020-03-03 20:33
 */
public class WeadReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference);
        o1 = null;
        System.gc();
        System.out.println("执行完GC后。。。。。。。。。。。。");
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}