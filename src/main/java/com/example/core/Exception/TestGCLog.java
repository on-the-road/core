package com.example.core.Exception;

/**
 * @Author wangwei
 * @Date 2019/6/3 16:01
 * -描述- 测试 jvm GC log相关
 */
public class TestGCLog {
    private static final  int _1MB = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20m  -Xmx20m  -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *  --    -Xmn10m  新生代分配10m内存，eden 8m  from 1m  to 1m
     *        所以新生代的可用内存最大是9m
     *  --    大对象直接进入老年代 -XX:PretenureSizeThreshold=3145728 ,有些收集器对此参数是无效的
     *  --    对象由年轻代进入老年代，受参数 -XX:MaxTenuringThreshold=15，默认是15 控制
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
        //allocation1 = new byte[2 * _1MB];
        //allocation2 = new byte[2 * _1MB];
        //allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; //todo 出现一次 Minor GC

    }
}
