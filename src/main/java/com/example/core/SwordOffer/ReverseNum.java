package com.example.core.SwordOffer;

/**
 * @Author wangwei
 * @Date 2019/7/4 14:58
 * -描述-
 */
public class ReverseNum {
    public static int reverseNum(int num){
        long rs = 0;
        for(; num != 0; rs = rs * 10 + num % 10, num /= 10){};
        System.out.println("反转后的num: " + rs);
        int result =  rs > Integer.MAX_VALUE || rs < Integer.MIN_VALUE ? 0 : (int) rs;
        System.out.println("反转后的返回结果：" + result);
        return result;
    }

    public static void main(String[] args) {
        reverseNum(2345);
        reverseNum(2147483647);
        reverseNum(-2147483648);
        //reverseNum(0);

        long temp = -8463847412L;
        System.out.println("Integer的最小值是：" + Integer.MIN_VALUE);
        boolean flag = temp < Integer.MIN_VALUE;
        System.out.println("比较结果： " + flag);
    }
}
