package com.example.core.test;

import com.example.core.domain.Person;

/**
 * 测试 一些java的基础问题
 */
public class Primer {
    public static void changeStr(String str){
        System.out.println("inner-before-operate:" + str);
        str = "append!";
        System.out.println("inner-after-operate:" + str);
    }

    public static void main(String[] args) {
        String str = new String("str-beging-");
        System.out.println("outer-before-operate:" + str);
        changeStr(str);
        System.out.println("outer-before-operate:" + str);

       /* Person person = new Person("Hello",44);
        System.out.println("outer-before-operate:" + person.getAge() + "," + person.getName());
        //调用方法
        changeAttribute(person);
        System.out.println("outer-after-operate:" + person.getAge() + "," + person.getName());*/
    }

    public static void changeBase(int a){
        System.out.println("inner-before-operate:" + a);
        a = 13;
        System.out.println("inner-after-operate:" + a);
    }

    public static  void  changeAttribute(Person person){
        person = new Person("suse",18);
        System.out.println("inner-before-operate:" + person.getAge() + "," + person.getName());
        person.setAge(100);
        person.setName("张三丰");
        System.out.println("inner-after-operate:" + person.getAge() + "," + person.getName());
    }



}
