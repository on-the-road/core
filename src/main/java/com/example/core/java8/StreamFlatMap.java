package com.example.core.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Author wangwei
 * @Date 2019/6/13 10:44
 * -描述- todo flatMap将Stream的每个元素转化到其他对象的Stream
 *        todo 因此，每个对象将被转换为零个、一个或多个基于Stream的不同对象
 *        todo 这些stream的内容将被放置到flatMap操作的返回Stream中
 */
public class StreamFlatMap {
    public static void main(String[] args) {
        List<Foo> foos = new ArrayList<>();

        //create foos
        IntStream.range(1,5)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        //create bars
        foos.forEach(f ->
            IntStream.range(1,5)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + "  <-" + f.name)))
        );
        //todo 现在我们有一个foo-list,里面有4个foo,而每个foo里都有一个bar-list,每个里面都有4个bar
        //todo flatMap接受一个函数，该函数必须返回对象Stream
        //todo 为了处理每个foo的bar对象，我们只需传递适当的函数

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

    }

}

class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name){
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name){
        this.name = name;
    }
}
