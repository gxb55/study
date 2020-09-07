package com.prepare.change.back;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Producer @Author guoxiaobing @Date 2020/8/8 16:29 @Version 1.0 @Description
 * 一个线程生产一个线程消费，两个线程交替打印ABABABAB
 */
public class Producer {

  public static String str = "A";
  public static Lock lockB = new ReentrantLock();
  public static Condition conditionB = lockB.newCondition();

  public static void main(String[] args) {

    for (int i = 0; i < 3; i++) {
      new Thread(
              () -> {
                product();
              })
          .start();
    }

    for (int i = 0; i < 3; i++) {
      new Thread(
              () -> {
                consumer();
              })
          .start();
    }
  }

  public static void product() {
    try {
      lockB.lock();
      while (!str.equals("A")) {
        conditionB.await();
      }
      str = "B";
      System.out.println("A");
      conditionB.signalAll();
    } catch (Exception e) {

    } finally {
      lockB.unlock();
    }
  }

  public static void consumer() {
    try {
      lockB.lock();
      while (!str.equals("B")) {
        conditionB.await();
      }
      str = "A";
      conditionB.signalAll();
      System.out.println("B");
    } catch (Exception e) {

    } finally {
      lockB.unlock();
    }
  }
}
