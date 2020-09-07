package com.prepare.change.back;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName ThreadDemo @Author guoxiaobing @Date 2020/7/28 9:21 @Version 1.0 @Description 线程相关内容
 */
public class ThreadDemo {
  private static final String A = "A";
  private static final String B = "B";
  private static AtomicInteger ai = new AtomicInteger(1);
  private static final AtomicStampedReference<String> ar = new AtomicStampedReference<>(A, 1);

  public static void main(String[] args) {
   /* AtomicInteger integer = new AtomicInteger(5);
    integer.getAndIncrement();
    integer.compareAndSet(4, 5);
    AtomicStampedReference atomicStampedReference = new AtomicStampedReference("A", 1);
    AtomicReference<Integer> atomicReference = new AtomicReference<>();
    atomicReference.compareAndSet(1, 2);*/
      test();
  }

  public static void test() {
    new Thread(
            () -> {
              try {
                Thread.sleep(Math.abs((int) (Math.random() * 100)));
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              if (ar.compareAndSet(A, B, 1, 2)) {
                System.out.println("我是线程1,我成功将A改成了B");
              }
            })
        .start();
    new Thread(
            () -> {
              if (ar.compareAndSet(A, B, ai.get(), ai.incrementAndGet())) {
                System.out.println("我是线程2,我成功将A改成了B");
              }
            })
        .start();
    new Thread(
            () -> {
              if (ar.compareAndSet(B, A, ai.get(), ai.incrementAndGet())) {
                System.out.println("我是线程3,我成功将B改成了A");
              }
            })
        .start();
  }
}
