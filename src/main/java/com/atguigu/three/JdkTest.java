package com.atguigu.three;

/**
 * @program: 2
 * @description: intern api的描述
 * @author: guoxiaobing
 * @create: 2020-11-01 17:32
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * tip
 * 1.深入了解java虚拟机
 * System--》initializeSystemClass -- 》sun.misc.Version.init(); --》
 * public class Version {
 *     private static final String launcher_name = "java";
 *
 * 2.VM.java  Version.java.template
 *
 *
 */
public class JdkTest {
    public static void main(String[] args) {
        String at = new StringBuilder().append("shangguigu").append("three").toString();
        System.out.println(at);
        System.out.println(at.intern());
        System.out.println(at == at.intern());
        String java = new StringBuilder().append("ja").append("va").toString();
        System.out.println(java);
        System.out.println(java == java.intern());
    }
}