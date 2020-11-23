package com.prepare.change;

import com.prepare.change.aop.annotation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ChangeApplicationTests {

    @Autowired
    private UserServiceImpl userService;
  //  @Test
    void contextLoads() {
        userService.add("1818");
    }

}
