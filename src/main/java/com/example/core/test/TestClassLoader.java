package com.example.core.test;

/**
 * @Author wangwei
 * @Date 2019/1/19 17:44
 * -描述-
 */
public class TestClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = TestClassLoader.class.getClassLoader();
        System.out.println(loader);
        //使用 ClassLoader.loadClass()来加载类，不会执行初始化块
        loader.loadClass("com.example.core.test.Test2");
        //使用 Class.forName()来加载类，默认会执行初始化块
        //Class.forName("com.example.core.test.Test2");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行初始化块
        //Class.forName("Test2",false,loader);

        //todo look  是用原生c++代码实现的,并不继承自java.lang.ClassLoader
        System.out.println("打印引导类加载器：" + String.class.getClassLoader());
    }

}
