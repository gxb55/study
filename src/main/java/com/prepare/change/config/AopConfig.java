package com.prepare.change.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @program: 2
 * @description:
 * @author: guoxiaobing
 * @create: 2020-11-22 17:33
 */
@Component
@Aspect
public class AopConfig {
    /**
     * 正常顺序 Around 最早 最晚
     *  @Before
     *  @Around
     *  @AfterReturning
     *  @After
     *
     * spring5:
     *  -----------------@Around Before
     * -----------------@Before
     * -----------------CallServiceImpl.getResult
     * -----------------@AfterReturning
     * -----------------@After
     * -----------------@Around After
     *
     * 异常
     *-----------------@Around Before
     * -----------------@Before
     * -----------------CallServiceImpl.getResult
     * SpringVersion:5.2.8.RELEASE,SpringBootVersion:2.3.2.RELEASE
     * -----------------@AfterThrowing
     * -----------------@After
     *
     *
     * spring4:
     * -----------------@Around Before
     * -----------------@Before
     * -----------------CallServiceImpl.getResult
     * SpringVersion:4.3.13.RELEASE,SpringBootVersion:1.5.9.RELEASE
     * -----------------@Around After
     * -----------------@After
     * -----------------@AfterReturning
     *
     * 异常情况：
     * -----------------@Around Before
     * -----------------@Before
     * -----------------CallServiceImpl.getResult
     * SpringVersion:4.3.13.RELEASE,SpringBootVersion:1.5.9.RELEASE
     * -----------------@After
     * -----------------@AfterThrowing
     */

    @Pointcut("execution(public * com.prepare.change.service.CallService.*(..))")
    public void aopGetResult(){
    }

    @Before("aopGetResult()")
    public void beforeNotify(){
        System.out.println("-----------------@Before");
    }
    @After("aopGetResult()")
    public void AfterNotify(){
        System.out.println("-----------------@After");
    }
    @Around("aopGetResult()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("-----------------@Around Before");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("-----------------@Around After");
        return proceed;
    }

    @AfterReturning("aopGetResult()")
    public void AfterReturning(){
        System.out.println("-----------------@AfterReturning");
    }
    @AfterThrowing("aopGetResult()")
    public void AfterThrowing(){
        System.out.println("-----------------@AfterThrowing");
    }

}