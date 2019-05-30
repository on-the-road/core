package com.example.core.test;

import java.util.concurrent.TimeUnit;

/**
 * @Author wangwei
 * @Date 2019/4/17 11:22
 * -描述-
 */
public class VolatileTest {
    //类变量
    final static  int max = 5;
    //todo 此处 volatile关键字的加与不加，直接影响程序的输出结果
    //todo 如果此时的变量是一个对象，那么对象里的属性，同样受volatile关键字的影响
    static volatile int value = 0;

    public static void main(String[] args) {
        //启动一个线程，当发现 max != value时，则输出value被修改的值
        new Thread(()->{
           int localValue = value;
           while (localValue < max){
               if(localValue != value){
                   System.out.println("The value is updated to" + value);
                   //对localValue进行重新复制
                   localValue = value;
               }
           }
        },"Reader").start();

        //启动另外一个线程，更新value的值，当value = max是，退出
        new Thread(()->{
            int localValue = value;
            while (localValue < max){
                System.out.println("The value will be updated to " + ++localValue);
                value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
