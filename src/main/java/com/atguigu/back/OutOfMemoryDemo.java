package com.atguigu.back;

/**
 * @program: 0311
 * @description: OOM实例
 * @author: guoxiaobing
 * @create: 2020-04-10 15:39
 */
public class OutOfMemoryDemo {
    public static void main(String[] args) {

        /**
         * java.lang.OutOfMemory StackOverFlow
         * java heap space
         */
    }

    private static void StackDemo() {
        /**
         * * java.lang.OutOfMemoryError StackOverflow  栈空间溢出 栈管运行的所以递归调用 -Xms5M -Xmx5M -XX:+PrintGCDetails
         * Exception in thread "main" java.lang.StackOverflowError
         */
        getNum();
    }

    public static void getNum() {
        getNum();
    }

    private static void HeapExample() {
        /**
         * java.lang.OutOfMemoryError： java heap space 堆空间溢出  堆管存储的所以new一个大对象即可 -Xms5M -Xmx5M -XX:+PrintGCDetails
         */
        System.out.println("复习哦");
        byte[] bt = new byte[1024 * 1024 * 6];
        String a = "sdf";
        String b = "sdf";
        while (true) {
           /* a = a + "" + (int) (Math.random() * 10) + "";
            b = a + "1";*/
        }
    }
}