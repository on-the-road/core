package com.example.core.test;

/**
 * @Author wangwei
 * @Date 2019/4/16 15:54
 * -描述-
 *     抽象类中可以没有抽象方法，但是一个类声明了是抽象类，则该类不能被实例化
 */
public abstract class AbstractTest {
    public void test1(){
        System.out.println("This is a common method!");
    }

    public static void main(String[] args) {
        //抽象类不能实例化
        //AbstractTest at = new
    }
}
