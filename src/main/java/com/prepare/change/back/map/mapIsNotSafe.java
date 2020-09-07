package com.prepare.change.back.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName mapIsNotSafe
 * @Author guoxiaobing
 * @Date 2020/8/11 9:19
 * @Version 1.0
 * @Description hashMap是线程不安全的
 */
public class mapIsNotSafe {
  public static void main(String[] args) {
    Map map = new HashMap();
    for (int j = 0; j < 50; j++) {
      new Thread(
              () -> {
                map.put(Math.random() * 10, Math.random() * 10 * 11);
                System.out.println(Thread.currentThread().getName() + "\t" + map);
              })
          .start();
    }

    for (int j = 0; j < 50; j++) {
      new Thread(
              () -> {
                map.put(Math.random() * 10 + 5, Math.random() * 10 * 23);
                System.out.println(Thread.currentThread().getName() + "\t" + map);
              })
          .start();
    }
  }
}
