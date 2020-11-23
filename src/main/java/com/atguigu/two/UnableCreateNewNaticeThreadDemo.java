package com.atguigu.two;

/**
 * @program: 0311
 * @description: java.lang.OutOfMemoryError unable to create new native thread
 * @author: guoxiaobing
 * @create: 2020-03-11 08:13
 * 每一个进场能够创建线程的数量会用限制 正常的linux环境限制是1024个 如果一个进场创建了太多的线程则会报错
 * 解决办法1.修改程序看实际情况到底需不需要这么多的线程 2.可以调节linux系统默认的线程数量
 * 为什么是 native方法 因为线程的start方法调用的就是native方法
 *
 *  java.lang.OutOfMemoryError: unable to create new native thread
 *  java.lang.OutOfMemoryError: direct buffer memory
 *  java.lang,OutOfMemoryError: StackOverflowError
 *  java.lang.OutOfMemoryError:java heap space
 *  java.lang.OutOfMemoryError:GC overhead limit execeded
 *
 *  ulimit -u
 *  vim /etc/security/limits.d/90-nproc.conf
 *
 */
public class UnableCreateNewNaticeThreadDemo {
    public static void main(String[] args) {
   /* Thread t1 = new Thread();
    t1.start();*/
        int i = 1;
        for(int j=1;;j++){
            System.out.println(j);
            new Thread(()->{
                try {
                Thread.sleep(Integer.MAX_VALUE);
                } catch (Exception e) {

                    System.out.println(e);
                } finally {

                }
            }).start();
        }
    }
}