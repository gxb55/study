package com.atguigu.two;

/**
 * @program: lvmama1
 * @description: 强引用
 * @author: guoxiaobing
 * @create: 2020-02-28 16:45
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 =new Object();
        Object o2 = o1;
        o1=null;
        System.gc();
        System.out.println(o2);
    }
}