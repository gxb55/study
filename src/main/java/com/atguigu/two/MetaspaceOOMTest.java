package com.atguigu.two;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: 0311
 * @description: java.lang, OutOfMemoryError Metaspacesize
 * @author: guoxiaobing
 * @create: 2020-03-11 08:38
 * jdk8中MetaSpace代替了永久代 这里把云空间设置小一点 然后不断的向云空间中装载东西
 * 就会报错
 * 云空间都存放：
 * 1.类的信息
 * 2.静态变量
 * 3.常量池
 * 4.及时编译后的代码
 * org.springframework.cglib.core.CodeGenerationException: java.lang.OutOfMemoryError-->Metaspace'
 * java.lang.OutOfMemoryError java heap space
 * stackoverflow
 */
public class MetaspaceOOMTest {
    static class OOMTest{}
    public static void main(String[] args) {
        int i= 0;
        try {
        while (true){
            i++;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMTest.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o,args);
                }
            });
            enhancer.create();
        }
        } catch (Exception e) {
            System.out.println("这是第" + i + "次 现在报错了吧");
            System.out.println(e);
        } finally {

        }
    }
}