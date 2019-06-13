package com.example.core.java8;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author wangwei
 * @Date 2019/6/12 10:21
 * -描述- 方法引用的规定
 *  //todo 实现抽象方法的参数列表，必须与方法引用方法的参数列表保持一致！至于返回值就不作要求
 *  //todo 先来说一下方法引用使用到的操作符“::”，这个操作符把方法引用分成两边
 *  //todo 左边是类名或者某个对象的引用，右边是方法名或者是“new”（构造器引用时用到）
 */
public class MethodReference {
    public static void main(String[] args) {
        //todo java内置函数式接口 对象引用::实例方法名
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello java-8 method-reference!");

        //todo java内置函数式接口 类名::静态方法名
        Function<Long,Long> function = Math::abs;
        System.out.println(function.apply(-34L));

        //todo java内置函数接口 类名::实例方法名
        BiPredicate<String,String> biPredicate = String::equals;
        System.out.println(biPredicate.test("abc","edc"));

        //todo 引用构造器 类名::new
        Function<Integer,StringBuffer> fis = StringBuffer::new;
        StringBuffer buffer = fis.apply(10);
        System.out.println(buffer.length());

        //todo 引用数组  类型[]::new
        Function<Integer,int[]> fii = int[]::new;
        int[] array = fii.apply(10);
        System.out.println(array.length);
    }
}
