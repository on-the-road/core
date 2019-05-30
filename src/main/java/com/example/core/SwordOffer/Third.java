package com.example.core.SwordOffer;

/**
 * @Author wangwei
 * @Date 2019/3/29 11:39
 * -描述- 在一个长度为n的数组里的所有数字都在0到n-1的范围内
 *       数组中某些数字是重复的，但不知道有几个数字是重复的
 *       也不知道每个数字重复几次。请找出数组中任意一个重复的数字
 *       要求是时间复杂度 O(N)，空间复杂度 O(1)因此不能使用排序的方法，也不能使用额外的标记数组
 *
 * -思路-
 *      对于这种数组元素在[0, n-1]范围内的问题，可以将值为i的元素调整到第i个位置上进行求解
 */
public class Third {
    public int duplicate(int[] nums,int len){
        if(nums == null || len <= 0) return -1;

        for (int i = 0; i < len; i++) {
            while (nums[i] != i){ //i位置上的元素不等于i
                if(nums[i] == nums[nums[i]]){ //索引i对应的元素，等于索引为（索引为i所对应的元素）所对应的元素
                    return nums[i];
                }
                swap(nums,i,nums[i]);
            }
            //继续遍历
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int [] arr = {3,2,1,2,4,5,1};
        System.out.println("找到的重复元素为--" + new Third().duplicate(arr,arr.length));
    }
}
