package com.example.core.java8;


import java.util.Optional;

/**
 * @Author wangwei
 * @Date 2019/6/13 11:33
 * -描述- todo FlatMap也可用于Java8引入的Optional类。
 *        todo Optionals flatMap操作返回另一个类型的可选对象
 */
public class OptionalFlatMap {
    public static void main(String[] args) {
        //todo 层次分明的结构 为了处理外部实例的内部字符串foo，必须添加多个空检查以防止可能的NullPointerExceptions
        Outer outer = new Outer();
        if(outer != null && outer.nested != null && outer.nested.inner != null){
            System.out.println(outer.nested.inner.foo);
        }

        //todo 利用Optional的 flatMap可以达到同样的效果
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);

        //todo 说实话 个人感觉没有上面的if判断简洁，虽然优雅...
    }
}

class Outer {
    //todo 嵌套的
    Nested nested;
}

class Nested {
    Inner inner;
}

class Inner {
    String foo;
}
