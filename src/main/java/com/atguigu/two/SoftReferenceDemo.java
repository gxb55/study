package com.atguigu.two;

import java.lang.ref.SoftReference;

/**
 * @program: lvmama1
 * @description: 软引用 内存够用就不回收 不够用就回收
 * @author: guoxiaobing
 * @create: 2020-02-28 16:58
 */
public class SoftReferenceDemo {
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> soft = new SoftReference<>(o1);
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(soft.get());

    }
    public static  void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> soft = new SoftReference<>(o1);
        o1 = null;
        System.out.println(o1);
        System.out.println(soft.get());

        System.out.println("-------------------------");

        try {
            Byte[] by = new Byte[1024*1024*50];
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            System.out.println(o1);
            System.out.println(soft.get());
        }


    }
    public static void main(String[] args) {
        //内存够的时候
       // softRef_Memory_Enough();
        //内存不够的时候
        softRef_Memory_NotEnough();
    }
}