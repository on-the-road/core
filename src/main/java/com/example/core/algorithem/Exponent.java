package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/4/13 14:15
 * -描述- 求一个数的n次方
 */
public class Exponent {
    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
     * param-base: 底数
     * param-exponent: 指数
     * 暴力法
     */
    public static double pow1(double base,int exponent){
        //任何数（0除外）的0次方都是1
        if(exponent == 0){
            return 1;
        }
        double temp = base;
        int n = exponent;
        if(n < 0){
            n = -n;
        }
        for (int i = 0; i < n; i++) {
            base *= temp;
        }
        return exponent < 0 ?  1/base : base;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
     * param-base: 底数
     * param-exponent: 指数
     * 通过位运算
     * 举个例子 --
     * base = 2  exponent = 13   13的二进制形式 == 1101
     * 2^13 = 2^1101 = 2^1000 * 2^0100 * 2^0001 = 2^8 * 2^4 * 2^1
     */
    public static double pow2(double base,int exponent){
        //任何数（0除外）的0次方都是1
        if(exponent == 0){
            return 1;
        }
        double temp = base;
        double sum = 1;
        int n = exponent;
        if(n < 0){
            n = -n;
        }
        while(n != 0){
            // & 运算符 -- 都为1时，则为1
            if((n & 1) != 0){
                sum *= temp;
            }
            temp *= temp;
            n = n >> 1;   //n右移一位
        }
        return exponent < 0 ?  1/sum : sum;
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println("右移一位：" + (n >> 1));
        System.out.println("右移二位：" + (n >> 2));
    }
}
