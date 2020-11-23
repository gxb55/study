package com.atguigu.two;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: 0311
 * @description: G1垃圾回收器
 * @author: guoxiaobing
 * @create: 2020-03-23 19:46
 */
public class G1Demo {
    public static void main(String[] args) {
        List list =  new ArrayList();
        String a="sdf";
        while (true){
            list.add(a+Math.random()*5020);
        }
        /**
         * G1垃圾回收器 参数：-Xmx10M -Xms10M -XX:+UseG1GC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
         * 跟CMS的比较 -XX:+UseConcMarkSweepGC
         * 1.不会产生垃圾回收的碎片
         * 2.可以设置垃圾回收的时间
         *
         *
         * 其他参数：
         * -XX：+G1HeapRegionSize = 5M  范围 1-32 M 默认分为2048个分区 2048*32=64G
         * 小区域收集+形成连续的内存块
         *
         * 初始标记；只标记GC Roots能直接关联到的对象 停止用户线程
         * 并发标记：进行GC Roots Tracing的过程 并发执行
         * 最终标记： 删除饼修正标记期间，因程序运行导致标记发送变化的那一部分 停止用户线程
         * 筛选回收：根据时间来进行价值最大化的回收 停止用户线程
         */
    }
}