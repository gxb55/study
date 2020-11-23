package com.prepare.change.controller;

import com.prepare.change.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: 2
 * @description:
 * @author: guoxiaobing
 * @create: 2020-11-22 17:25
 */
@RestController
public class MainController {
    @Autowired
    private CallService callService;

    @RequestMapping("/getTime")
    public Object getTime(){
        Map map = new HashMap<>();
        map.put("time", LocalDateTime.now());
        return map;
    }

    @GetMapping("/getResult")
    public Object getResult(@RequestParam String x, @RequestParam  String y){
      int result =  callService.getResult(Integer.parseInt(x),Integer.parseInt(y));
        Map map = new HashMap<>();
        map.put("result", result);
        return map;
    }
}