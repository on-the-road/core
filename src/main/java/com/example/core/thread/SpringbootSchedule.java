package com.example.core.thread;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author wangwei
 * @Date 2019/4/11 10:47
 * -描述- springboot 定时器的几种模式
 */
@Component
public class SpringbootSchedule {

   /* @Scheduled(fixedDelay = 3*1000,initialDelay = 2*1000)
    public void schedule1() throws InterruptedException {
        System.out.println();
        System.out.println("now-date:" + formatter.format(LocalDateTime.now()));
        Thread.sleep(5*1000);
    }*/
   /* @Scheduled(cron = "0/5 * * * * ?")
    public void schedule2() throws InterruptedException {
        System.out.println();
        System.out.println("now-date:" + formatter.format(LocalDateTime.now()));
        Thread.sleep(6*1000);
    }*/
    /**
     * springboot 定时任务的几种模式
     */
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 3*1000,initialDelay = 2*1000)
    public void schedule3() throws InterruptedException {
        System.out.println();
        System.out.println("now-date:" + formatter.format(LocalDateTime.now()));
        Thread.sleep(4*1000);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10 ; ++i) {
            System.out.println("i = " + i);
        }
    }

}
