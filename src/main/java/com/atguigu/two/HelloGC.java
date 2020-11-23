package com.atguigu.two;

/**
 * @program: lvmama
 * @description: GC 相关内容
 * @author: guoxiaobing
 * @create: 2020-02-24 20:30
 */
public class HelloGC {
    public static void main(String[] args) throws Exception {
        /**
         * jvm 参数三种
         * 1.标配参数 java -version -help
         * 2.x参数
         * 3.xx参数
         *      3.1
         *
         *
         *      jinfo 查看正在运行的程序
         *      jps -l 查看所有的java程序
         *
         *      -Xms:初始堆大小 64分之一
         *      -Xmx:最大堆大小 四分之一
         */
        System.out.println("Hello GC");
        //Thread.sleep(Integer.MAX_VALUE);
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java 虚拟机的内存容量
        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"(字节)，"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+"(字节)，"+(maxMemory/(double)1024/1024)+"MB");
        Thread.sleep(Integer.MAX_VALUE);
        /**
         * 初始化jvm参数 使用的命令
         * java -XX:+PrintFlagsInitial 初始化的内容
         * java -XX:+PrintFlagsFinal 被修改后的值
         * java -XX:+PrintCommandLineFlags 查询默认值
         *
         * -XX:InitialHeapSize=266553600 堆内存初始值 64分之一
         * -XX:MaxHeapSize=4264857600  堆内存最大值 4分之一
         * -XX:+PrintCommandLineFlags
         * -XX:ThreadStackSize=1048576  栈内存 1024b
         * -XX:+UseCompressedClassPointers
         * -XX:+UseCompressedOops
         * -XX:-UseLargePagesIndividualAllocation
         * -XX:+UseParallelGC  并行垃圾回收机制
         */
    }
}