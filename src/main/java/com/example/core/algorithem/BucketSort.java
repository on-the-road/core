package com.example.core.algorithem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author wangwei
 * @Date 2018/11/21 17:25
 * -描述- 桶排序
 */
public class BucketSort {
    public static double[] bucketSort(double[] array){
        //1.得到序列的最大最小值,并算出差值d
        double max = array[0];
        double min = array[0];

        for(int i = 1; i < array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
            if(array[i] < min){
                min = array[i];
            }
        }
        double d = max - min;

        //2.初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> list = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++){
            list.add(new LinkedList<Double>());
        }

        //3.遍历原始数组，将每一个元素放入桶中
        //桶的数量 == 原始数列元素的数量,区间跨度 = （最大值-最小值）/（桶的数量 - 1）
        //除了最后一个桶是[m,n],其他桶都是[m,n)
        for (int i = 0; i < array.length; i++){
            //这里的计算逻辑是？？
            int num = (int) ((array[i] - min) * (bucketNum - 1)/d);
            list.get(num).add(array[i]);
        }

        //4.对每个桶内部进行排序
        for (int i = 0; i < bucketNum; i++){
            //JDK底层采用了归并排序或归并的优化版本
            Collections.sort(list.get(i));
        }

        //5.输出全部的元素
        double[] sortedArray = new double[bucketNum];
        int index = 0;
        for (LinkedList<Double> element:list){
            for (double obj:element){
                sortedArray[index] = obj;
                index++;
            }
        }
        return  sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[]{1.2,0,3.23,0.05,5.123,4,0.9,3.003,9,6.89};
        System.out.println(Arrays.toString(bucketSort(array)));
    }
}
