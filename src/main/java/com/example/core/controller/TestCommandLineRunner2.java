package com.example.core.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author wangwei
 * @Date 2018/10/29 17:05
 * -描述-
 */

@Component
@Order(value = 2)
public class TestCommandLineRunner2  implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("----------这是第二个我要预先加载的资源类....");
    }
}
