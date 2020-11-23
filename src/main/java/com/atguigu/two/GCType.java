package com.atguigu.two;

/**
 * @program: 0311
 * @description: GC垃圾回收的种类
 * @author: guoxiaobing
 * @create: 2020-03-13 07:50
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 垃圾回收分为四种垃圾收集器
 * 1.serial 串行的 需要暂停用户线程来收集垃圾切收集垃圾的线程是单线程的不适合生产环境 -XX:+UseServialGC
 * 2.parallel 并行的 跟串行的差不多 差别就是并行的垃圾回收的时候用的是多个线程  -XX:+UseParallelGC
 * 3.CMS 用户线程一边运行 gc一边回收生产上面多用于这种垃圾回收器  ;标记清除
 * 4.G1
 *
 * STW stop the world
 *
 * UseSerialGC
 * UseSerialOldGC
 *
 * UseParallelGC
 * UseParallelOldGC
 *
 *
 * UseParNewGC
 * UseConcMarkSweepGC
 *
 *
 * UseG1GC
 *
 *young Gen: Serial Copying
 *old Gen:
 *
 *
 *
 * 四种垃圾回收算法
 * 1.引用计数 现在基本上都不用了姐姐姐不了循环引用的问题
 * 2.复制拷贝 将空间分为相同的两半，每次从这一半中回收然后放入到另一半中 年轻代中用的比较多
 * 3.标记清除  将要回收的垃圾标记 然后清除 这样会产生内存碎片 老年代用的多
 * 4.标记整理  每次标记之后将垃圾移到内存的一边 清除另一边这样不会有碎片基本问题全部解决 但是效率会比较底下
 *
 *
 *
 * UseSerialGC 单线程处理使用这个参数后 在年轻代和年老代都会使用串行垃圾收集器 serial （young区用） serial old （Old区用）
 * 表示 新生代 老年代都会用串行垃圾回收器
 */
public class GCType {
    public static void main(String[] args) throws Exception{
        System.out.println("Hello GC Type..");
        String a = "asdf";
        List list = new ArrayList();
        while (true){
            list.add(a+Math.random()*100);
        }
       /* byte[] bytes = new byte[1024*11*1024];
        Thread.sleep(Integer.MAX_VALUE);*/
    }
    /**
     * 1.-XX:+UseSerialGC -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
     * [GC (Allocation Failure) [DefNew: 2724K->320K(3072K), 0.0018430 secs][Tenured: 647K->967K(6848K), 0.0022553 secs] 2724K->967K(9920K), [Metaspace: 3303K->3303K(1056768K)], 0.0041576 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * DefNew,Tenured,Metaspace 新生代老年代都是用串行化垃圾回收器 单个线程来回收垃圾
     *
     * 2.-XX:+UseParNewGC -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
     * [GC (Allocation Failure) [ParNew: 2722K->320K(3072K), 0.0007873 secs][Tenured: 673K->990K(6848K), 0.0018972 secs] 2722K->990K(9920K), [Metaspace: 3303K->3303K(1056768K)], 0.0027340 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [Tenured: 990K->948K(6848K), 0.0017631 secs] 990K->948K(9920K), [Metaspace: 3303K->3303K(1056768K)], 0.0017831 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * -XX:ParallelGCThreads=5  每次用多少个线程来回收垃圾 一般情况下跟你的cpu的核数是一样的
     * ParNew:年轻代使用多线程并行垃圾收集器
     * Tenured：老年代使用单线程即串行垃圾回收器
     *老年代要使用串行垃圾回收即 serialGC 现在不推荐这样来做了
     * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
     *
     * 3.-Xmx10M -Xms10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
     * [GC (Allocation Failure) [PSYoungGen: 504K->488K(2560K)] 1058K->1091K(9728K), 0.0004514 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 488K->0K(2560K)] [ParOldGen: 602K->875K(7168K)] 1091K->875K(9728K), [Metaspace: 3138K->3138K(1056768K)], 0.0054124 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     *PSYoungGen  ParOldGen 新生代老年代都是用并行垃圾回收器 加快系统的吞吐量 系统运行100分钟 1分钟用来回收垃圾 那系统吞吐量就是 99%
     *
     * 4.-Xmx10M -Xms10M -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails
     * 新生代使用 parNew并发清除 老年代是用ConcMarkSweepGC 以及serialGC
     * 其中serialGC是一个备用的收集器，因为CMS要保证在老年代
     * CMS分为四步
     * 1.CMS-initial-mark: 标记GCRoot可以达的对象  单独进行
     * 2.CMS-concurrent-mark-start 标记GCRoot可以达的对象引用的对象 跟用户线程一起进行
     * 3.Final Remark 再次确认这些对象是否需要回收 即方才不可达现在可达了 有一个再次确认的过程
     * 4.mark-sweep 清除标记的对象
     *
     * cms ConcMarkSweepGC
     * 优点：并发处理效率高响应快
     * 缺点：1.并发执行对CPU造成的压力大 2. 采用的标记清除会产生内存碎片
     *
     * -XX：UseParallelGC 跟 -XX:UseParallelOldGC 相互激活
     *
     * undertow是个什么鬼？
     * 内置服务器跟tomcat划等号
     * Jetty 和 UnderTow 是SpringBoot
     *
     * java -jar study.jar
     * java -server jvm参数 -jar study.jar  带jvm参数执行jar包
     */
}