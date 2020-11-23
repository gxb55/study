package com.atguigu.two.jdk8;


/**
 * @program: 0311
 * @description: Lambda表达式
 * @author: guoxiaobing
 * @create: 2020-04-02 20:26
 */
@FunctionalInterface
interface Foo {
    //  public void sayHello();

    public int add(int a, int b);

    public default int dev(int c, int d) {
        return c + d;
    }

    public static int division(int a, int b) {
        return a / b;
    }
}

/**
 * 1.括号粘过来，写死右箭头，落地大括号
 * 2.FunctionalInterface
 * 3.detault 修饰的方法
 * 4. static方法
 *
 * 之前都是用匿名内部类来实现的
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        // System.out.println();
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("我是老式的方法");
            }

            @Override
            public int add(int a, int b) {
                return 0;
            }
        };*/

       /* Foo foo1 = ()->{
          System.out.println("我就是lambda 函数式编程，面向接口编程");
        };*/
        Foo foo = (int a, int b) -> {
            return a + b + 50;
        };

        System.out.println(foo.add(5, 6));
        System.out.println(foo.dev(3, 56));
        foo.add(5, 6);
        System.out.println(Foo.division(40, 10));

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };

        Thread thread1 = new Thread(()->{

        },"tt");

        Runnable runnable = ()->{
          System.out.println("您好啊");
        };

        Comparable comparable = (Object o)->{

            return 0;
        };
    }

}