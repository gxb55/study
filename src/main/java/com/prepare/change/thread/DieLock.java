package com.prepare.change.thread;

/** @ClassName DieLock @Author guoxiaobing @Date 2020/8/15 11:31 @Version 1.0 @Description 死锁 */
public class DieLock {
  private DieLock() {};

  private String A = "A";
  private String B = "B";
  private static DieLock dieLock;

  public static void main(String[] args) {
    DieLock lock = new DieLock();
    new Thread(
            () -> {
              try {
                lock.getA();
              } catch (Exception e) {

              }
            },
            "A")
        .start();

    new Thread(
            () -> {
              try {
                lock.getB();
              } catch (Exception e) {

              }
            },
            "B")
        .start();
  }

  public static DieLock getLock() {
    if (dieLock == null) {
        synchronized (dieLock){
            if (dieLock==null){
                dieLock = new DieLock();
            }
        }
    }
    return dieLock;
  }

  public void getA() throws InterruptedException {
    synchronized (this.B) {
      Thread.sleep(1000);
      synchronized (this.A) {
      }
    }
  }

  public void getB() throws InterruptedException {
    synchronized (this.A) {
      Thread.sleep(1000);
      synchronized (this.B) {
      }
    }
  }
}
