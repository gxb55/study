package com.atguigu.two;


import com.atguigu.util.JSON;
import org.springframework.boot.json.JacksonJsonParser;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: lvmama1
 * @description: 一些测试的代码
 * @author: guoxiaobing
 * @create: 2020-03-10 10:25
 */
public class demo {
    public static void main(String[] args)throws Exception {
        String addr = InetAddress.getLocalHost().getHostAddress();
        System.out.println(addr);
        List list = new ArrayList();
        list.add("172.31.30.132");
        list.add("172.31.30.138");
        System.out.println(JSON.toJSON(list));
        int i=0;
        i = i++ +i;
        System.out.println(i);
        int j = 0;
        Integer integer = new Integer(0);
        boolean equals = integer.equals(j);
        System.out.println(equals);

    }
}