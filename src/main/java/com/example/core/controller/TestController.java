package com.example.core.controller;

import com.example.core.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jp")
public class TestController {
    @Autowired
    Task task;

    @RequestMapping("/jsp")
    public String goJsp(){
        return "";
    }

    @RequestMapping("/task")
    public String task() throws Exception{
        task.doTaskThree();
        task.doTaskTwo();
        task.doTaskOne();
        return "success";
    }
}
