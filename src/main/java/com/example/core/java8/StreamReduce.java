package com.example.core.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2019/6/13 11:46
 * -描述- todo Stream中 reduce的用法，reduce 是减少，缩小的意思
 *        todo reduce的操作将Stream中的所有元素合并到一个结果当中
 *        todo java8中有三种reduce方法
 */
public class StreamReduce {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Max",18),
                new Person("Peter",23),
                new Person("Pamela",23),
                new Person("David",12)
        );

        //todo 第一种将Stream中元素reduce为一个
        persons.stream()
                .reduce((p1,p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                .ifPresent(System.out::println);

        //todo 第二种通过聚合其他元素，构造新的实体

        Person newPerson = persons.stream()
                .reduce(new Person("",0),
                        (p1,p2) -> {
                            p1.setAge(p1.getAge() + p2.getAge());
                            p1.setName(p1.getName() + p2.getName());
                            return p1;
                });
        System.out.println("reduce聚合后：[age=" + newPerson.getAge() + ", name=" + newPerson.getName()+ "]");

        //todo 第三种接受三个参数，标示值，累加器，组合函数
        Integer ageSum = persons.stream()
                .reduce(0, //起始值
                        (sum,p) -> sum += p.getAge(), //累加器
                        (sum1,sum2) -> sum1 + sum2 //组合函数
                );
        System.out.println("汇总的年龄总和是：" + ageSum);
    }
}
