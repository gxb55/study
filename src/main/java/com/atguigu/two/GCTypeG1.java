package com.atguigu.two;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: 0311
 * @description: G1 GC垃圾回收器
 * @author: guoxiaobing
 * @create: 2020-03-21 20:04
 */
public class GCTypeG1 {
    public static void main(String[] args) {
        System.out.println(123);
        String a="ss";
        List list = new ArrayList();
        while (true){
            list.add(a+Math.random()*5000);
        }

        /**
         * G1垃圾回收器：-Xmx10M -Xms10M -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseG1GC
         * Heap
         *  garbage-first heap   total 10240K, used 857K [0x00000000ff600000, 0x00000000ff700050, 0x0000000100000000)
         *   region size 1024K, 1 young (1024K), 0 survivors (0K)
         *  Metaspace       used 3400K, capacity 4500K, committed 4864K, reserved 1056768K
         *   class space    used 364K, capacity 388K, committed 512K, reserved 1048576K
         *
         *   堆是分 年轻代 老年代 云空间
         *   现在不是的了
         *   跟之前的垃圾收集器的区别
         *   1.年轻代和老年代是各自独立且连续的内存块
         *   2.年轻代收集使用eden+s0+s1进行复制算法
         *   3.俩年代收集必须扫描整个老年代区域
         *   4.都是以尽可能少而快捷地执行GC为设计原则的
         */

    }
}