package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/3/4 10:14
 * -描述- 计算阶乘
 */
public class Factorial {
    public static void f1(int n){
        //特例 0！= 1, n >= 0
        long factorial = 1;
        for(int i = 2; i <= n; i++ ){
            factorial *= i;
        }
        System.out.println("result:" + factorial);
    }

    public static void main(String[] args) {
        //计算5的阶乘
        f1(50);
    }

}
