package com.prepare.change.aop.annotation;

import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author guoxiaobing
 * @Date 2020/9/7 17:56
 * @Version 1.0
 * @Description 用户信息
 */
@Service
public class UserServiceImpl {
    public void add(String name){
        System.out.println(name);
    }
}
