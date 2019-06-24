package com.example.core.jvm;

/**
 * @Author wangwei
 * @Date 2019/6/10 15:21
 * -描述-
 */
public class SuperClass {
    public static final int a = 13;
    static {
        System.out.println("Super init!");
    }
}

class SubClass {
    static {
        System.out.println("Sub init!");
    }
    public static void main(String[] args) {
        System.out.println(SuperClass.a);
    }
}
