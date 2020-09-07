package com.prepare.change.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName demo1
 * @Author guoxiaobing
 * @Date 2020/8/3 15:31
 * @Version 1.0
 * @Description equals
 */
public class demo1 {
  public static void main(String[] args) {
      boolean equals = "1".equals(1);
      System.out.println(equals);
      Map map = new ConcurrentHashMap();
      List list = new CopyOnWriteArrayList();
      list.add(1);
      Set set = new ConcurrentSkipListSet();
      set.add(1);
  }
}
