package com.example.core.java8;

import java.util.Objects;

/**
 * @Author wangwei
 * @Date 2019/5/24 13:57
 * -描述-
 */
public class ObjectsRequireNonNull {

    public static void main(String[] args) {
        //todo 如果是参数是null 同样会抛出NullPointerException，意义何在？
        //todo 不使用 Objects.requireNonNull 同样也是抛出空指针异常
        //todo 如果此判断行为放在构造对象的时刻，那么就能控制抛出异常的时间点
        //todo 也就是说异常会尽早的抛出，在构建对象的时候就抛出了，而不是等到业务逻辑调用的时候才抛出
        //todo 仅此而已
        //Object obj = Objects.requireNonNull(null);
        Object obj = Objects.requireNonNull(12);
        boolean flag = Objects.isNull(new String());
        System.out.println("obj -- :" + obj);
        System.out.println("flag -- :" + flag);
    }
}
