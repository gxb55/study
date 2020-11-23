package com.atguigu.two;

/**
 * @program: lvmama
 * @description: java -XX:+PrintFlagsInitial  java -XX:+PrintFlagsFinal
 * PrintGCDetails
 * @author: guoxiaobing
 * @create: 2020-02-25 21:14
 */
public class TT {
    public static void main(String[] args) {
        int a=50;
        int b = 50;
        int c =a+b;
        System.out.println(c);
    }
}