package com.atguigu.two.jdk8;

import java.util.concurrent.TimeUnit;

/**
 * @program: 0311
 * @description: 8 锁演示
 * @author: guoxiaobing
 * @create: 2020-04-04 11:06
 */

/**
 * 1.标准访问，请问先打印邮件还是短信
 * 2.暂停4秒钟在邮件方法，请问先打印邮件还是短信
 *
 * 3.新增普通sayHell方法，请问先打印邮件还是短信
 * 4.两部手机，请问先打印邮件还是短信
 *
 * 5.两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6.两个静态同步方法 两部手机，请问先打印邮件还是短信
 *
 * 7.一个静态同步方法，一个普通同步方法 同一部手机，请问先打印邮件还是短信
 * 8.一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信
 *
 *
 *
 * 一个对象里面如果有多个synchronized方法，某一时刻内，只要有一个线程去调用其中的synchronized方法了，
 * 其他的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronize方法。一个线程调用对象的synchronize
 * 方法锁了这个对象的，这个对象的其他synchronize方法也需要等待
 *
 * 锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他的synchronize方法
 *
 * 加了普通方法后发现跟同步锁无关
 *
 * 换成两个对象后 不是同一个把锁了 情况改变了 即锁的不是一个对象了
 *
 * synchronize实现同步锁的基础：java中的每一个对象都可以作为锁
 * 具体表现为以下三种形式
 * 对于普通同步方法，锁的是当前对象，锁的是this
 * 对于静态同步方法，锁的是当前类的对象 静态方法属于类，所有new出来的对象点静态方法点出来的都是一个
 * 比如静态变量i=1,任何一个对象修改了这个i 其他的也能随之改动
 *
 */
class Phone {

    public synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("*****sendEmail");
    }

    public synchronized void sendMSG() throws Exception {
        System.out.println("*****sendMSG");
    }

    public void sayHello() throws Exception {
        System.out.println("*****sayHello");
    }
}

public class Lock8Demo5 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try {
                //  phone.sendMSG();
                phone.sayHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}