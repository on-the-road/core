package com.example.core.algorithem;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @Author wangwei
 * @Date 2019/4/15 11:10
 * -描述- 数据流中寻找中位数
 */
public class MedianFinder {
    /**
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * [2,3,4] 的中位数是 3
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5

     * 设计一个支持以下两种操作的数据结构：
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数
     *
     * 实现思路：
     * 数据流 -- 动态数据，如果使用数组，每次添加一个元素都要排序，效率很低
     * 动态数据常用的数据结构是，堆，栈，队列，二叉树
     * 使用堆，数据分成两部分，大顶堆和小顶堆中
     * 大顶堆中的数据都要比小顶堆中的数据小 ？
     * 为了分配均匀，二个堆中的数据数目之差不能超过1
     *
     */

    public PriorityQueue<Integer> minheap,maxheap;

    public MedianFinder(){
        //维护较大元素的小顶堆 -- 堆顶的元素值最小
        //todo Colletions.reverseOrder() -- 降序排列比较器
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
        //维护较小元素的大顶堆 -- 堆顶的元素值最大
        minheap = new PriorityQueue<>();
    }

    public void addNum(Integer num){
        maxheap.add(num);
        minheap.add(maxheap.poll());
        if(maxheap.size() < minheap.size()){
            maxheap.add(minheap.poll());
        }
    }

    //returns the median of current data stream
    public double medianFind(){
        if(maxheap.size() == minheap.size()){
            return (minheap.peek() + maxheap.peek()) * 0.5;
        }else {
            return maxheap.peek();
        }
    }
}
