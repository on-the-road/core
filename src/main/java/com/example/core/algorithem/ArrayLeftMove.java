package com.example.core.algorithem;

import java.util.Arrays;

/**
 * @Author wangwei
 * @Date 2019/5/27 14:44
 * -描述-
 * - 给定一个数组和正整数n（n小于数组长度），请将此数组循环左移n个位置。
        example：
        输入：[1,3,5,8,6],3
        输出：[8,6,1,3,5]
 */
public class ArrayLeftMove {
    /**
     * 解题思路，分为三步
     * 1.把数组分成两个部分，划分的标准就是左移的位数后面为界限进行划分，分为两个部分
     * 2.分别对左右两个部分进行逆序
     * 3.对整个部分（第二步的结果）进行逆序
     */

    /**
     * 数列逆序操作
     * @param array 要逆序的数组
     * @param left 需要逆序的左索引
     * @param right 需要逆序的右索引
     */
    public static void reverseArray(int [] array,int left,int right){
        int temp;
        //数据交换
        while (left < right){
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 数列左移操作
     * @param array 要操作的数组
     * @param n 左移的位数
     */
    public static void leftMove(int[] array,int n){
        if(array == null || array.length == 0) return;
        reverseArray(array,0,n - 1);
        reverseArray(array,n,array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        //todo 划分的两部分 分别进行逆序
        leftMove(array,4);
        //todo 划分的两部分逆序之后，对操作后的整个部分进行逆序处理
        reverseArray(array,0,array.length - 1);
        //todo 打印操作后的结果
        System.out.println(Arrays.toString(array));
    }
}
