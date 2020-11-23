package com.atguigu.two;

import java.nio.ByteBuffer;

/**
 * @program: 0311
 * @description: 直接内存溢出会跑出的异常java.lang.OutofMemoryError
 * @author: guoxiaobing
 * @create: 2020-03-11 07:46
 *
 *  ByteBuffer.allocate(10); // 在堆里面分配内存 jvm可以回收
 *         ByteBuffer.allocateDirect(500);//在堆外的直接内存中分配内存 如果直接内存过小则报异常
 *         java.lang.OutOfMemoryError Direct bugger memory
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory: "+(sun.misc.VM.maxDirectMemory() /1024/1024d )+"MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(60 * 1024 * 1024);
        System.out.println("44e20ba8cda7cce73deb1e20acb271eccc0579ff95d2afa5e781127e0f26a92b".length());
    }
}