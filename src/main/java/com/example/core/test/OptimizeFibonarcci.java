package com.example.core.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Author wangwei
 * @Date 2019/1/31 11:52
 * -描述-
 */

public class OptimizeFibonarcci {
    //普通写法
    public int fibo1(int n){
        if(n <= 2){
            return n;
        }else{
            return fibo1(n -1) + fibo1(n - 2);
        }
    }

    //保存状态写法(备忘录法)
    public int fibo2(int n){
        int[] status = new int[n + 1];
        if(n <= 2){
            return n;
        }else{
            if(status[n] == 0){
                status[n] = fibo2(n - 1) + fibo2(n - 2);
            }
            return status[n];
        }
    }

    public static void main(String[] args) throws Exception {
    }

}
