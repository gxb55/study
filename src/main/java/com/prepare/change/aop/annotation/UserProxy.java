package com.prepare.change.aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserProxy
 * @Author guoxiaobing
 * @Date 2020/9/7 17:57
 * @Version 1.0
 * @Description 代理类
 */
@Component
@Aspect
public class UserProxy {

    @Before(value = "execution(* com.prepare.change.aop.annotation.UserServiceImpl.add(..))")
    public void before(){
        System.out.println("before");
    }
}
