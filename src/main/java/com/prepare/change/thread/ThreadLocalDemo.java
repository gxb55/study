package com.prepare.change.thread;

import java.util.concurrent.Executors;

/**
 * @ClassName ThreadLocalDemo
 * @Author guoxiaobing
 * @Date 2020/8/12 9:38
 * @Version 1.0
 * @Description ThreadLocal
 */
public class ThreadLocalDemo {
    public static ThreadLocal<String> local = new ThreadLocal<>();
  public static void main(String[] args) {
    new Thread(()->{
        local.set("thread1");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(local.get());

    }).start();

      new Thread(()->{
          local.set("thread2");
          local.set("thread12");
          System.out.println(local.get());
      }).start();

  }


}
