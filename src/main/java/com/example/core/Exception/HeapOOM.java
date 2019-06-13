package com.example.core.Exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2019/6/3 14:10
 * -描述- 模拟堆内存溢出
 */
public class HeapOOM {
    static class OOMObject{

    }

    /**
     * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }

    }
}
