package com.atguigu.two;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @program: lvmama1
 * @description: WeahHashmap demo 是map的一种，当key为null的时候 执行gc 就会将这个键值对从map中移除
 * @author: guoxiaobing
 * @create: 2020-03-03 20:58
 */
public class WeakHashmapDemo {
    public static void main(String[] args) {
        //TestHashMap();
        System.out.println("==========================================");
        TestWeahHashMap();
    }



    private static void TestHashMap() {
        HashMap<Integer, String> hashMap = new HashMap();
        Integer key = new Integer(1);
        String value = "HashMap";
        hashMap.put(key, value);
        System.out.println(hashMap);
        key = null;
        System.out.println(hashMap);
        System.gc();
        System.out.println(hashMap + "," + hashMap.size());
    }
    private static void TestWeahHashMap() {
        WeakHashMap<Integer, String> hashMap = new WeakHashMap<Integer, String>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        hashMap.put(key, value);
        System.out.println(hashMap);
        key = null;
        System.out.println(hashMap);
        System.gc();
        System.out.println(hashMap + "," + hashMap.size());

    }
}