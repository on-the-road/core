package com.example.core.Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author wangwei
 * @Date 2019/5/22 17:02
 * -描述- Optional是 Java 8 新增的特性，能更好的处理空值 或者说更优雅的处理空值
 * - 如何构造一个Optional
    Optional.of(T value): value不可为null，否则直接NPE
    Optional.ofNullable(T value): value可为空
    Optional.empty(): 构造一个值为null的Optional对象
    对于一个Optional，value是不可变得，只能通过以上前2个方法初始化value
    Optional 是一个安全的类

    --- Optional 的最佳实践
    Optional的最佳实践
    禁止
        Optional不能被序列化，因此不能使用于字段
    不建议
        Optional不建议用于函数的入参。试想传入一个Optional.empty()有多奇怪，可以使用相同函数名来避免。
        不建议在使用isPresent后接着使用get，那将和用==判断无异，不能体现Optional的优越性，反而麻烦。
    建议
        建议使用于函数的返回值，提醒调用者对null的处理，也使调用者也变得优雅起来～
        建议使用于连续调用，减少if(obj != null)的判断，也不失可读性，可谓优雅。看如下例子
 */
public class TestOptional {
    Map<String, Map<String, Object>> map; // 我们不知道是否为null
    public TestOptional(Map<String,Map<String,Object>> map){
        this.map = map;
    }

    // 不使用 Optional
    public void optionA(){
        Object ret1 = null;

        if (map != null) {
            Map<String, Object> tmp = map.get("k");
            if (tmp != null) {
                ret1 = tmp.get("kk");
            }
        }
        if (ret1 == null) {
            ret1 = new Object();
        }
        System.out.println("获取到数据地址：" + ret1.toString());

    }

    // 使用 Optional，优雅！
    public void optionB(){
        Object ret2 = Optional.ofNullable(map) // Optional<Map<String, Map<String, Object>>>
                .map(kkv -> kkv.get("k")) // Optional<Map<String, Object>>
                .map(kv -> kv.get("kk")) // Optional<Object>
                .orElseGet(Object::new); // 或者 orElse(new Object())
        System.out.println("Optional处理获取到数据地址：" + ret2.toString());
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("kk","hello,study-optional");
        Map<String,Map<String,Object>> wrapper = new HashMap<>();
        wrapper.put("k",map);

        TestOptional to = new TestOptional(wrapper);
        to.optionA();
        to.optionB();

        System.out.println("Integer-最小值：" + Integer.MIN_VALUE);
        System.out.println("Integer-最小值的绝对值：" + Math.abs(Integer.MIN_VALUE));

        // i + 1 < i  Integer.MAX_VALUE
        // i != 0 && i == -i  Integer.MIN_VALUE
        //todo Infinity [ɪnˈfɪnəti]  无穷大
        double i = Double.POSITIVE_INFINITY;
        System.out.println("撩妹神数：" + i);
        System.out.println("撩妹神数：i + 1 == i：" + (i == i + 1) );
        System.out.println("撩妹神数：i - 1 == i：" + (i == i - 1));

        // i + 1 = i  Double.NaN





    }


}
