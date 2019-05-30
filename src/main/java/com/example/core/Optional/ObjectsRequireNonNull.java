package com.example.core.Optional;

import java.util.Objects;

/**
 * @Author wangwei
 * @Date 2019/5/24 13:57
 * -描述-
 */
public class ObjectsRequireNonNull {

    public static void main(String[] args) {
        Object obj = Objects.requireNonNull(12);
        boolean flag = Objects.isNull(new String());
        System.out.println("obj -- :" + obj);
        System.out.println("flag -- :" + flag);
    }
}
