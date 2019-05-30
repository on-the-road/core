package com.example.core.test;

/**
 * @Author wangwei
 * @Date 2019/2/21 10:30
 * -描述-
 */
public class TestSynchronized {
    public synchronized void test1(){
        System.out.println("hello");
    }

    public void test2(){
        synchronized (this){
            System.out.println("world");
        }
    }
}
