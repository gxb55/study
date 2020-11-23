package com.atguigu.two.jdk8;

/**
 * @program: 0311
 * @description: 类加载器
 * @author: guoxiaobing
 * @create: 2020-04-07 20:16
 */

/**
 * 类加载器分四种
 * 1.启动类加载器 c++实现的 bootstrap
 * 2.扩展类加载器 extension
 * 3.应用程序类加载器 appClassloader
 * <p>
 * 4.自己写类加载器 继承抽象类 ClassLoader
 *
 * 应用程序 -》类加载器加载 -》堆 heap -》方法区（类的模板信息 method area）  线程不共享的
 *
 * 本地方法栈 native stack ，虚拟机栈 stack 程序计数器  线程共享
 *
 *
 * 1.什么是类加载器
 * 2.有几种
 * 3.什么是双亲委派
 * 4.什么是沙箱安全机制
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println("类加载器");
        Object o = new Object();
        System.out.println(o.getClass().getClassLoader());
        System.out.println();
        System.out.println();
        System.out.println();

        Demo1 demo1 = new Demo1();
        System.out.println(demo1.getClass().getClassLoader());//应用程序类加载器
        System.out.println(demo1.getClass().getClassLoader().getParent());//扩展类加载器
        System.out.println(demo1.getClass().getClassLoader().getParent().getParent());//启动类加载器


    }
}