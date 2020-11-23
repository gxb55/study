package com.atguigu.two;

/**
 * @program: lvmama1
 * @description: OutOfMemoryError:java heap size 实例
 * @author: guoxiaobing
 * @create: 2020-03-04 20:47
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        /**
         * java.lang.OutOfMemoryError :java heap space
         * 程序中对象过多引起堆内存溢出
         *
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */
        Byte[] bytes = new Byte[80 *1024 *1024];
    }
}