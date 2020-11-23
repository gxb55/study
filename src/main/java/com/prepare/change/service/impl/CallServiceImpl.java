package com.prepare.change.service.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import com.prepare.change.service.CallService;

/**
 * @program: 2
 * @description:
 * @author: guoxiaobing
 * @create: 2020-11-22 17:16
 */
@Service
public class CallServiceImpl implements CallService {
    @Override
    public int getResult(int x, int y) {
        System.out.println("-----------------CallServiceImpl.getResult");
        System.out.println("SpringVersion:"+SpringVersion.getVersion()+","+"SpringBootVersion:"+ SpringBootVersion.getVersion());
        int result =x/y;
        return result;
    }
}