package com.atguigu.two;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;

/**
 * @program: lvmama
 * @description: spring源码相关内容
 * @author: guoxiaobing
 * @create: 2020-02-18 17:49
 */
public class Testproxy {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
         annotationConfigApplicationContext.getBean("123");
        //spring aop是通过代理来实现的 代理有可能是jdk的也有可能是spring的 具体在生成代理的时候会进行判断
        // 最后的实现类是 DefaultAopProxyFactory
        //jdk的代理 具体的实现类是 class Proxy
        /**
         * 为什么jdk的代理要实现接口因为jdk在实现代理的时候默认的继承了proxy这个类
         * java是单继承多实现的 所以要实现接口
         */
        int j;
        for (int i = 1; i < 5; i++) {
            j = i++;
            System.out.println(j);
        }
        System.out.println("====================");

        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "$Proxy", new Class[]{UserService.class});

        FileOutputStream fm = new FileOutputStream("D://Proxy.class");
        fm.write(proxyClassFile);
        fm.flush();
    }
}