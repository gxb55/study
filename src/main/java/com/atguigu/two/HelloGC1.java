package com.atguigu.two;

/**
 * @program: lvmama1
 * @description: gc的具体参数
 * @author: guoxiaobing
 * @create: 2020-02-28 13:50
 */
public class HelloGC1 {
    public static void main(String[] args) throws Exception {
        System.out.println("hello gc");
        Thread.sleep(Integer.MAX_VALUE);
        //Byte[] by =new Byte[1024*1024*10];
        /**
         * [GC (Allocation Failure) [PSYoungGen: 2048K->512K(2560K)] 2048K->930K(9728K), 0.0027190 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * GC用在年轻代      年轻代从2048 --》 512 2560   jvm堆内存 收集前2048 -》930 9728 总共   用户时间 系统时间 真正时间
         * 收集前是2048 收集后是512 总共是 2560
         * [Full GC (Allocation Failure) [PSYoungGen: 512K->0K(2560K)] [ParOldGen: 522K->834K(7168K)] 1034K->834K(9728K), [Metaspace: 3304K->3304K(1056768K)], 0.0050669 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
         * Full GC 老年代
         * 类型 从多少到多少 总共多少 四个方面
         * PrintGCDetails
         * 堆分为新生代 老年代 比例是1：2
         * 新生代分为 eden from to 8：1：1
         *
         *
         *  eden space 69632K, 68% used [0x000000076b400000,0x000000076e2cce88,0x000000076f800000)
         *   from space 8704K, 0% used [0x0000000770080000,0x0000000770080000,0x0000000770900000)
         *   to   space 8704K, 0% used [0x000000076f800000,0x000000076f800000,0x0000000770080000)
         *
         *
         *
         *
         *   -XX:SurvivorRatio=8 设置年轻代中伊甸园区的比例 正常都是 8：1：1
         *   -XX:NewRatil=2 设置老年代在堆内存中占比 年轻代:老年代 1：2
         *
         */
    }
}