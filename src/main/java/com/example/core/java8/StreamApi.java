package com.example.core.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author wangwei
 * @Date 2019/6/12 11:46
 * -描述- //todo Stream表示元素的序列，并支持这些元素进行不同类型的计算操作
 */
public class StreamApi {
    public static void main(String[] args) {
        //todo Stream操作包括中间操作和终端操作
        //todo 中间操作返回Stream，所以我们可以串联多个中间操作

        //todo 终端操作返回void 或一个非Stream的结果值
        //todo 整个操作链 -- 就是操作管道，大多数Stream操作接受lambda表达式参数

        List<String> myList = Arrays.asList("a1","a2","b1","c1","c2");
        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        //todo 可以从各种数据源创建Stream，特别是Collections、Set、List
        //todo 支持新方法 Stream()和 paralleStream()，创建顺序或并行Stream
        //todo 并行Stream可以在多个线程中运行

        //todo 从一堆对象引用中创建Stream,不一定需要集合来创建Stream
        Stream.of("a1","a2","a3")
                .findFirst()
                .ifPresent(System.out::println);

        //todo 除了常规的对象Stream,java8引入了特殊类型的Stream
        //todo you maybe guessed! 它们用于处理基本类型 int、long、double
        //todo IntStream、 LongStream、DoubleStream

        IntStream.range(1,6)
                .forEach(System.out::println);  //1,2,3,4,5

        //todo 原生Stream与普通的Stream一样工作，但是有不同
        //todo 原生Stream使用专门的lamdba表达式  IntFunction而非Function、IntPredicate而非Predicate
        //todo 原生的Stream支持额外的终端操作，sum()和average()

        Arrays.stream(new int[]{1,3,5,3,6})  //返回的是一个IntStream
                .map(x -> x * 2 + 1)
                .average()  //41除以5 == 8.2
                .ifPresent(System.out::println);

        //todo 有时 原生Steam与普通Stream需要互相转化，不可避
        //todo 普通的Stream支持 mapToInt()、mapToLong()、mapToDouble()

        Stream.of("a1","a2","a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        //todo 原生Stream可以通过 mapToObj()转化成普通Stream

        IntStream.range(1,4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        //todo 一个组合的示例
        Stream.of(1.3,2.0,3.3)
                .mapToInt(Double::intValue)
                .mapToObj(s -> "a" + s )
                .forEach(System.out::println);

        //todo 不存在四舍五入，直接取整
        System.out.println(new Double(9.98).intValue());
        System.out.println("我是一个分割线------------------------------");
        //todo 中间操作的一个重要特征是惰性，中间操作只在出现终端操作时执行
        Stream.of("d1","a2","a3","b1","b3","c")
                .filter(s ->{
                        System.out.println("filter" + s);
                        return true;
                });
        //执行上面的代码，控制台没有任何的输出，why? 惰性，看下面的代码才有输出
        Stream.of("d1","a2","a3","b1","b3","c")
                .filter(s ->{
                    System.out.println("filter" + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach" + s));

        //todo anyMatch()，返回true时，退出方法
        System.out.println("我是一个分割线------------------------------");
        Stream.of("d1","e2","a3","b1","b3","c")
                .map(s ->{
                    System.out.println("filter: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {     //返回true时，退出
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        //todo 为什么顺序很重？可以大大的减少实际的执行次数，看下面两个例子
        //todo 为什么能减少执行的次数，因为一次次的过滤，过滤掉不符合要求的元素
        System.out.println("我是一个分割线------------------------------");
        Stream.of("d1","a2","b1","b3","c")
                .map(s ->{
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
        //输出了5次map,5次filter,一次forEach,我不妨调换一下顺序再看输出结果
        System.out.println("我是一个分割线------------------------------");
        Stream.of("d1","a2","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s ->{
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println("我是一个分割线------------------------------");
        //todo 增加一个额外的中间操作，来扩展上面的示例 sorted
        //todo sorted 是一种特殊的中间操作，就是所谓的改变状态操作
        //todo 因为要对元素进行排序，你需要维护元素的状态？
        //todo 换句话说，sorted操作是水平执行的...

        Stream.of("d1","a2","b1","b3","c")
                .sorted((s1,s2) ->{
                    //这里注意格式化符号 %s -- 不要写成 s% !
                    System.out.printf("sorted: %s - %s\n",s1,s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s ->{
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println("我是一个分割线------------------------------");
        //todo java8 Stream 无法复用，一旦对一个Stream调用终端操作，Stream就会关闭

        Stream<String> stream =Stream.of("a1","b2","c3")
                .filter(s -> s.startsWith("a"));
        //调用终端操作
        stream.anyMatch(s -> true);  //ok
        //stream.noneMatch(s -> true); //exception

        //todo 那么怎么来复用呢？通过Stream的提供者来每次创建一个新的Stream链

        Supplier<Stream<String>> streamSupplier = ()->
                Stream.of("a1","b2","c3")
                .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true); // ok
        streamSupplier.get().noneMatch(s -> true); //ok




    }



}
