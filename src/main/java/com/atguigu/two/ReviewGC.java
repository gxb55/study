package com.atguigu.two;

/**
 * @program: lvmama1
 * @description: 复习GC的相关参数
 * @author: guoxiaobing
 * @create: 2020-03-03 19:26
 */
public class ReviewGC {
    public static void main(String[] args) throws Exception {
        System.out.println("begin-------");
        /**
         * 1.java -version
         * 2.java -X:
         * 3.java -XX:
         *  +
         *  -
         *  -XX:MetaspaceSize=10m
         */
        //学习的GC参数有
        /**
         * 1.-Xms 初始化最大堆内存 ; 1和2最好设置成一样的大小
         * 2.-Xmx 初始化堆内存
         * 3.-XX:ThreadStackSize 栈大小  10m
         *
         */
        Thread.sleep(Integer.MAX_VALUE);
    }
}