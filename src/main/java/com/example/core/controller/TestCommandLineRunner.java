package com.example.core.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author wangwei
 * @Date 2018/10/29 17:02
 * -描述-
 * 在使用SpringBoot构建项目时，我们通常有一些预先数据的加载。那么SpringBoot提供了一个简单的方式来实现–CommandLineRunner。
 * CommandLineRunner是一个接口，我们需要时，只需实现该接口就行。如果存在多个加载的数据，我们也可以使用@Order注解来排序。
 */

@Component
@Order(value = 1)
public class TestCommandLineRunner  implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("------------------这是第一个我要预先加载的资源类....");
    }
}
