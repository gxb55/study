package com.atguigu.two;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: lvmama1
 * @description: GC Overhead 异常 简单讲就是程序大部分时间都在执行gc 而且收效甚微 所以程序就直接报错
 * @author: guoxiaobing
 * @create: 2020-03-04 21:08
 */
public class GCOverheadDemo {
    public static void main(String[] args) throws Exception {
        /**
         * java.lang.OutOfMemoryError: GC overhead limit exceeded
         *
         * java.lang.OutOfMemoryError : GC overhead limit exceeded
         */
        System.out.println("======================================");
        int i = 0;
        List<String> list = new ArrayList();
        while (true){
            try {
                list.add(String.valueOf(i++).intern());
            } catch (Exception e) {
                System.out.println(";;;;;;;;;;;;;;;" + i);
                throw e;
            }
        }
    }
}