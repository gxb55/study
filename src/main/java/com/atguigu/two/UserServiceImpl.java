package com.atguigu.two;

/**
 * @program: lvmama
 * @description: 实现类 用来测试代理的
 * @author: guoxiaobing
 * @create: 2020-02-18 20:58
 */
public class UserServiceImpl implements UserService {
    @Override
    public void saynb(String name) {
        System.out.println(name+"niubi .....");
    }
}