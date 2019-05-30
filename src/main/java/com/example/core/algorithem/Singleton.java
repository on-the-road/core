package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2018/10/23 15:56
 * -描述-
 */
public class Singleton {
    //私有构造
    private Singleton() {
    }

    //私有实例
    private static volatile Singleton instance;

    //公共获取实例
    public static Singleton getInstance() {
        //只有instance为null才会进入同步代码块中
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
