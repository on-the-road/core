package com.example.core.algorithem;

import java.util.Arrays;

/**
 * @Author wangwei
 * @Date 2019/2/19 13:30
 * -描述- 快速排序 -- 非稳定排序
 * -最好时间复杂度 O(nlogn)
 * -最坏时间复杂度 O(n*n)
 * -是对冒泡排序的一种改进，基本思想是选取一个记录作为枢轴，经过一趟排序
 *  将整段序列分为两个部分，其中一部分的值都小于枢轴，另一部分都大于枢轴
 *  然后继续对这两部分继续进行排序，从而使整个序列达到有序
 * -基于分治的思想以及采取递归的方式来处理子问题
 */
public class QuickSort {

    /**
     * 分割调整,数组arr的范围为[left, right],双向扫描写法
     * 选取第一个元素为主元
     * @param arr  给定的要排序的数组
     * @param left  起始下标为left
     * @param right  末尾下标为right
     */
    public static int partition_double(int[] arr, int left, int right){
        int pivot = arr[left]; //pivot存放主元
        int i = left + 1;
        int j = right;
        while (true){
            //向右遍历扫描，i向右遍历的过程中，如果遇到大于或等于主元的元素时则停止移动
            while (i <= j && arr[i] < pivot) {i++;}
            //while (i <= j && arr[i] <= pivot) {i++;} //正确写法
            //while (i < j && arr[i] <= pivot) {i++;} //错误写法

            //向左遍历扫描，j向左遍历的过程中，如果遇到小于或等于主元的元素则停止移动
            while (i <= j && arr[j] > pivot ) {j--;}
            //while (i <= j && arr[j] >= pivot ) {j--;} //正确写法
            //while (i < j && arr[j] >= pivot ) {j--;} //错误写法

            //i和j都停止移动时，如果这时i < j，则交换 i, j 所指向的元素
            if(i >= j) break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //直到 i >= j,把arr[j]与主元交换,这里有坑，此时i=j为什么交换arr[i]会出问题
        /**
         * 交换枢纽元素与j元素：为什么不与i交换？很多书上没有写，如果不思考一下，写成与i元素交换，就会导致错误的结果
         * 首先要理解：i停下时，i元素总是大于或等于枢纽元素，j停下时，j元素总是小于或等于枢纽元素
         * 与i还是j交换关键看枢纽元素的位置，这里是选第一个元素作为枢纽元素
         * 由于此次排序完成后第一个元素的值应该小于或等于枢纽元素，所以要找一个小于或等于枢纽元素的元素放到start位置
         * 也就是j所指向的元素
         */
        arr[left] = arr[j];
        arr[j] = pivot;
        //把主元的下标返回
        return j;
    }

    /**
     * 分割调整,数组arr的范围为[left, right],单向扫描写法
     * 选取最后一个元素为主元
     * @param arr  给定的要排序的数组
     * @param left  起始下标为left
     * @param right  末尾下标为right
     */
    public static void partition_single(int[] arr, int left,int right){
        if(left >= right) return;
        int i = left;
        int pivot = arr[right];
        //两个下标 i，j分别指向left，j从左向右遍历
        //遍历的过程中，如果遇到比主元小的元素，则把该元素与i指向的元素交换，并且 i = i +1
        for(int j = left; j < right; j++){
            if(arr[j] < pivot){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        //就这样，直到j = right
        //把i指向的元素与主元进行交换，交换之后，i左边的元素一定小于主元，而i右边的元素一定大于或等于主元
        arr[right] = arr[i];
        arr[i] = pivot;
        //此时，递归子问题(主元的左右两部分呢)
        partition_single(arr,left,i-1);
        partition_single(arr,i+1,right);

    }


    public static void quick_sort(int[] arr,int left, int right){
        if(left < right){
            int center = partition_double(arr,left,right);
            System.out.println("center -- " + center);
            quick_sort(arr,left, center - 1);
            quick_sort(arr,center + 1, right);
        }
    }

    public static void main(String[] args) {
        int [] arr = {5,3,23,14,0,34,19,8,36,5};
        //quick_sort(arr,0,arr.length - 1);
        partition_single(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }



}
