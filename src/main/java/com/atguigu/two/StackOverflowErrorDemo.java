package com.atguigu.two;

/**
 * @program: lvmama1
 * @description: OOM之 栈溢出 一般栈都是512-1024kb
 * @author: guoxiaobing
 * @create: 2020-03-04 20:39
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        /**
         * java.lang.StackOverflowError
         * Exception in thread "main" java.lang.StackOverflowError
         */
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}