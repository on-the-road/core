package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/6/17 14:40
 * -描述- todo 算法中的一些奇淫巧技
 */
public class SmartAlgor {
    public static void main(String[] args) {
        //todo  & 运算符 判断一个正整数是否是2的幂次方
        //todo 如果一个数n是2的幂次方，意味着这个n的二进制表示中，只有一个位是1，其他都是零
        //todo  2^0 = 0001   2^1 = 0010  2^2 = 0100  2^3 = 1000
        //todo 所以呢，我们只需要判断N中的二进制表示法中是否只存在一个 1 就可以了
        int n = 8;
        System.out.println(n & (n - 1));
    }
}
