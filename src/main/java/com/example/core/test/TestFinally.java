package com.example.core.test;

/**
 * @Author wangwei
 * @Date 2019/7/4 17:13
 * -描述- todo 测试  finally和teturn的关系
 */
public class TestFinally {
    public static int testFinally(){
        int a = 7;
        try {
            a++;
            return a;
        } finally {
            a = 9;
            return a;
        }
    }

    public static String testString(){
        String a = "a";
        try {
            a = "b";
            return a;
        }finally {
            a = "c";
            return a;
        }
    }

    public static void main(String[] args){
        int target = testFinally();
        System.out.println("结果值：" + target);

        String tar = testString();
        System.out.println("结果值：" + tar);


        System.out.println("ClassLoader-Parent:" + ClassLoader.getSystemClassLoader());
    }
}
