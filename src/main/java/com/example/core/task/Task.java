package com.example.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class Task {
    private final Logger log = LoggerFactory.getLogger(Task.class);
    private static Random random = new Random();

    //在定义了线程池之后，我们如何让异步调用的执行任务使用这个线程池中的资源来运行呢？
    //方法非常简单，我们只需要在 @Async注解中指定线程池名即可
    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时:" + (end - start) + "毫秒。");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时:" + (end - start) + "毫秒。");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时:" + (end - start) + "毫秒。");
    }

    @Async("taskExecutor")
    public Future<String> run () throws Exception{
        long sleep = random.nextInt(10000);
        log.info("开始执行任务，耗时:" + sleep + "毫秒。");
        Thread.sleep(sleep);
        log.info("完成任务");
        return  new AsyncResult<>("Hello");
    }


}
