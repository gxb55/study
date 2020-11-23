package com.atguigu.two.jdk8;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: 0311
 * @description: 集合类大复习
 * @author: guoxiaobing
 * @create: 2020-04-04 10:34
 */
public class CollectDemo {
    public static void main(String[] args) {
        /**
         * Map kv键值对
         *      HashMap HashTable ConcurrentHashMap
         * List
         *      ArrayLis LinkedList CopyOnWriteArrayList
         * Set
         *      HashSet TreeSet CopyOnWriteArraySet
          */
        Set set = new CopyOnWriteArraySet();
        Map map  = new ConcurrentHashMap();
        /**
         *                    Collection
         *       set            list            queue
         *      AbstractSet     AbstractList    BlockQueue
         *      实现类         实现类             实现类
         *
         *
         *                      Map
         *      HashMap      HashTable      ConcurrentHashMap
         *
         */
    }
}