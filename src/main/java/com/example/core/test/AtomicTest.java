package com.example.core.test;


import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author wangwei
 * @Date 2019/6/18 9:55
 * -描述-
 */
public class AtomicTest {
    private static AtomicLong param = new AtomicLong();

    public static void main(String[] args) {
        var a = 17;
        System.out.println("JDK11,新玩法： var:" + a);
        //todo 注意这里 字符数组是数组 数组有length属性，字符串有length()方法
        String b = "s";
        System.out.println(b.substring(0,1));
    }

}
