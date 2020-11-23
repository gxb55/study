package com.atguigu.two;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @program: lvmama1
 * @description: 虚引用 调用get返回null 常常和ReferenceQueue一起使用
 * @author: guoxiaobing
 * @create: 2020-03-04 20:17
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        System.out.println();
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference<Object> reference = new PhantomReference<Object>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("+++++++++++++++++++++++++++++++++++");
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());
        /**
         * GCROOTs
         *  1.栈中的局部变量表中引用的对象
         *  2.方法区中静态属性引用的对象
         *  3.方法区中常量引用的对象
         *  4.本地方法栈native引用的对象
         */
    }
}