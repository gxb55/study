package com.atguigu.two.jdk8;

/**
 * @program: 0311
 * @description: HashMap1.8所做的改动
 * @author: guoxiaobing
 * @create: 2020-03-29 14:53
 */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.针对于之前的HashMap数据结构做复习 其实就是jdk1.7的
 * HashMap 是允许null key null value的但是null的key只能有一个
 * HashMap默认大小是16 加载因子是0.75 当容量大于16*0.75的时候就会进行扩容，每次扩容都是原来的两倍因为他在扩容的时候是按位来运算的
 * 即16-》32-》64 ;HashMap的数据结构在1.7 的时候是数组加链表
 * jdk1.7一个元素存入HashMap：数据结构是 数组+链表
 * 1.计算他的hash值 根据hash值放入到数组的某个下标中去；
 * 2.如果多个元素的hash值是相同的就会产生链表的结构 每次相同的元素会放在链表的头部；
 * 3.每次hash值相同则称为是碰撞 如果碰撞产生的多了则就会影响到效率，因为每次存入的时候会跟链表上的每一个元素equals影响效率
 * <p>
 * jdk1.8的时候元素存入HashMap 数据结构是 数组+链表+红黑树
 * 这里前面都和1.7是一样的在元素总数大于64 单条链表长度大于8的时候会用红黑树来代替链表
 * 使用了红黑树在存入的时候会影响到效率，但是在取出的时候会大大提高效率，将红黑树的左子元素都为小于当前元素的  将右子元素都设置大于当前元素的
 * 这样在碰撞后插入就会快很多，在扩容的时候比如一个元素的hash值是5扩容后的位置就是 HashMap的长度加上5
 * HashMap改变了则HashSet也会随之改变因为 HashSet用的就是HashMap的key Hashset的value是固定值
 * 在new HashSet<>(); 时候底层源码显示的就是  map = new HashMap<>();
 * 在add方法的时候是
 * public boolean add(E e) {
 * return map.put(e, PRESENT)==null;
 * }
 * 其中    private static final Object PRESENT = new Object();
 * <p>
 * <p>
 * 2.HashMap相关的基础内容复习
 * 3.jdk1.8他做的改动
 */
public class HashDemo {
    public static void main(String[] args) {
        Set set = new HashSet<>();
        set.add(null);
        set.add(null);
        System.out.println(set);
        Map map = new HashMap<>();
        map.put(null, 1);
        map.put(null, 2);
        System.out.println(map);


        /**
         * ConcurrentHashMap 线程安全
         * 1.7 分段锁实现线程安全 每次只操作其中一段
         * 1.8 使用的CAS 比较并且交换  // no lock when adding to empty bin
         */
        ConcurrentHashMap<Object, Object> conCurrent = new ConcurrentHashMap<>();
        conCurrent.put(1, 1);

        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());

        //OPtional opt = new Optional.ofNullable(objectObjectMap);

/**
 *内存分类
 * PermGenSize  非堆就是方法区
 * MaxPermGenSize 最大非堆内存
 * 现在是 MetaspaceSize 云空间
 * MaxMetaspaceSize 最大的云空间
 * Metaspace 现在是用的物理机的内存 oom减少了
 *
 */
    }
}