package com.example.core.Exception;

import java.util.List;

/**
 * @Author wangwei
 * @Date 2019/6/13 15:13
 * -描述-
 */
public class ForEachNull {
    public static void main(String[] args) {
        List<String> list = null;
        for(String s: list){ //todo 这里抛出NullPointerException
            System.out.println(s);
        }

        list.forEach(s -> System.out.println(s));  //todo 这里抛出NullPointerException
    }
}
