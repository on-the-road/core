package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/7/4 10:13
 * -描述- 动态规划  Dynamic Programming  -- DP问题
 */
public class DynamicProgramming {
    /**
     * todo 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m
     * todo 每个物品的大小为A[i]：m=30,A[17,5,6,7,10]---最满为28  17 + 5 + 6 + 10
     * todo 首先寻找状态，确定将什么作为状态，记录状态，有背包和物品，物品有放和不放两种状态
     * todo 放置的时候可能会对应各种容量
     * todo 当前的容量下可以放置进的最多的物品取决于上一个物品放置时在该状态下所能够达到的最大状态和当前物品的的大小
     * todo 这样我们在最后，就可以得到每种容量下，所能放置的物品的最大数量
     */

    public static int backPack1(int m, int[] a){  //m - 背包的容量  a - 每个物品
        //边界条件
        if(m == 0 || a == null || a.length == 0){
            return 0;
        }
        //物品的个数
        int len = a.length;
        //初始化一个二维数组
        int[][] sum = new int[len][m + 1];
        //边界为零，初始化为零
        for(int i = 0; i < len; i++){
            //容量为0时
            sum[i][0] = 0;
        }
        for (int j = 0; j < m+1; j++){
            //a[0] = 17,j,0-30
            if(a[0] < 0){
                sum[0][j] = a[0];
            }
        }

        for(int i = 1; i < len; i ++){
            for (int j = 1; j < m + 1; j ++){
                //物品与背包容量进行比较
                if(a[i] < j ){
                    //todo 这里不好理解？
                    sum[i][j] = Math.max(sum[i - 1][j],sum[i - 1][j -a[i]]) + a[i];
                }

            }
        }

        return 0;
    }
}
