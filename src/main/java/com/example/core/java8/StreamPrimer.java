package com.example.core.java8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author wangwei
 * @Date 2019/6/12 15:45
 * -描述- //todo Stream的一些高级用法 collect、 flatMap、reduce
 *       // todo 收集、水平的、减少或缩减
 */
public class StreamPrimer {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Max",18),
                new Person("Peter",23),
                new Person("Pamela",23),
                new Person("David",12)
        );

        //todo collect是一种非常有用的终端操作，可以将Stream元素转化成不同类型的结果
        List<Person> list1 = persons.stream()
                .filter(p -> p.getName().startsWith("P"))
                .collect(Collectors.toList());
        System.out.println("collect解雇结果：" + list1);

        System.out.println("--------分割线--------");
        Map<Integer,List<Person>> ageMap =
                persons.stream()
                .collect(Collectors.groupingBy(p -> p.getAge()));

        //遍历生成的map,可以看到分组很成功 ---
        ageMap.forEach((age,person) -> System.out.printf("age %s-%s\n",age,person));
        //todo collectors是多功能的，你还可以在Stream元素上创建聚合

        Double averageAge = persons.stream()
                .collect(Collectors.averagingInt(p -> p.getAge()));
        System.out.println("平均年龄是：" + averageAge);

        //todo collectors可以创建汇总信息,总数，最大，最小，算术平均值，总和
        IntSummaryStatistics iss =
                persons.stream()
                .collect(Collectors.summarizingInt(p -> p.getAge()));

        System.out.println("总数：" + iss.getCount());
        System.out.println("最小：" + iss.getMin());
        System.out.println("最大：" + iss.getMax());
        System.out.println("算术平均值：" + iss.getAverage());
        System.out.println("总和：" + iss.getSum());

        System.out.println("汇总信息--：" + iss);

        System.out.println("--------分割线--------");

        //todo collectors.joining("separator","prefix","subfix")
        //todo join collector接受一个分隔符以及可选的前缀和后缀

        String phrase = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getName())
                .collect(Collectors.joining(" and ","Hello "," nice to meet you !"));
        System.out.println("合成的句子：" + phrase);

        //todo 为了把Stream转化成Map，我们必须指定键和值如何映射
        //todo 请记住，映射的键必须是唯一的，否则会抛出IllegalStateException
        //todo 你可以将合并函数作为额外参数传递，以绕过异常
        System.out.println("--------分割线--------");
        Map<Integer,String> map = persons.stream()
                .collect(Collectors.toMap(
                        p -> p.getAge(), //指定键如何映射
                        p -> p.getName(), //指定值如何映射
                        (name1,name2) -> name1 + ":" + name2  //如果键是相同的，值怎么处理，给出处理的方案来避免异常
                ));

        System.out.println("转化成的map：" + map);

        //todo 构建自己专用的 collector,我们通过 Collector.of() 创建了一个新的Collector
        //todo 我们必须传递Collector的四个要素:supplier、accumulator、 combiner和finisher
        //todo supplier - 提供器 accumulator - 收集器 combiner - 组合器 finisher - 结果处理器

        Collector<Person,StringJoiner,String> personalCollector =
                Collector.of(
                        () -> new StringJoiner("|"),   //supplier 构造字符串帮助类
                        (j,p) -> j.add(p.getName().toUpperCase()), //accumulator 将每个符合要求的元素填充到StringJoiner中
                        (j1,j2) -> j1.merge(j2), //combiner 如何将两个Stringjoiner合并成一个
                        StringJoiner::toString //finsher 从StringJoiner中构造出所需的字符串
                );
        String names = persons.stream()
                .collect(personalCollector);
        System.out.println("自定义采集器：" + names);

    }
}

class Person{
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
