package com.example.core.algorithem;

import java.util.Arrays;

/**
 * @Author wangwei
 * @Date 2019/5/14 17:51
 * -描述- 给定一个数组，利用堆找出 top N个数，这里利用小顶堆
 */
public class FindBigNumberWithHeap {
    //todo 堆的实现方式是数组，与数组索引的关系
    //给定一个节点的索引 其父节点索引
    private int parent(int n){
        return (n - 1)/2;
    }
    //给定一个节点的索引 其左孩子节点的索引
    private int left(int n){
        return 2 * n + 1;
    }

    //给定一个节点的索引 其右孩子节点的索引
    private int right(int n){
        return 2 * n + 2;
    }

    //todo 构建堆 这里用到是小顶堆
    private void buildHeap (int n, int [] data){
        for (int i = 1; i < n; i++){
            int t = i;
            //调整堆
            while (t != 0 && data[parent(t)] > data[t]){ //
                int temp = data[t];
                data[t] = data[parent(t)];
                data[parent(t)] = temp;
                //todo 这一步？t = parent(t) 保持原始的t
                t = parent(t);
            }
        }
    }

    /**
     * 大顶堆  -- 实现升序排列，why?
     1.二叉堆本质上是一种完全二叉树
     2.最大堆的堆顶是整个堆中的最大元素
     当我们删除一个最大堆的堆顶（并不是完全删除，而是替换到最后面），经过自我调节
     第二大的元素就会被交换上来，成为最大堆的新堆顶
     由于二叉堆的这个特性，我们每一次删除旧堆顶，调整后的新堆顶都是大小仅次于旧堆顶的节点
     那么我们只要反复删除堆顶，反复调节二叉堆，所得到的集合就成为了一个有序（升序）集合

     * 小顶堆  -- 实现降序排列，why?
     1.二叉堆本质上是一种完全二叉树
     2.最小堆的堆顶是整个堆中的最小元素
     当我们删除一个最小堆的堆顶（并不是完全删除，而是替换到最后面），经过自我调节
     第二小的元素就会被交换上来，成为最小堆的新堆顶
     由于二叉堆的这个特性，我们每一次删除旧堆顶，调整后的新堆顶都是大小仅大于旧堆顶的节点
     那么我们只要反复删除堆顶，反复调节二叉堆，所得到的集合就成为了一个有序（降序）集合
     */

    /**
     * 最小堆的调整方法
     * @param a
     * @param start -- 被下调节点的起始位置，一般从0开始
     * @param end  --  截止位置，一般是数组中最后一个元素的索引
     */
    private void min_heap_down(int[] a, int start, int end) {
        int current = start;
        int left = left(current);
        int temp = a[current];
        for (; left <= end; current = left,left = left(left)){
            //todo 这里第一次遍历, 第三项不做计算。left--是左孩子，left + 1 -- 是右孩子
            if(left < end && a[left] > a[left + 1]){
                left++;
            }
            //与较小的进行比较
            if(temp <= a[left]){ //调整结束
                break;
            }else { //交换value
                a[current] = a[left];
                a[left] = temp;
            }
        }
    }

    //todo 分别是数组本身 数组的长度
    public void heapSort(int [] a,int n) {
        //从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        //每次窃取最小堆的堆顶元素，放到数组的末尾，把窃取之后的堆再次调整为最小堆
        for (int i = n - 1; i > 0; i--) {
            //交换a[0]和a[i]
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            //交换后，保证a[0...i-1]任然是一个最小堆
            min_heap_down(a, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        FindBigNumberWithHeap fbnw = new FindBigNumberWithHeap();
        int [] a = {23,45,12,30,21,78,45,10,56,66,92,8,16,47,2};

        fbnw.buildHeap(a.length,a);
        System.out.println("构建完堆的数组结构:" + Arrays.toString(a));
        fbnw.heapSort(a,a.length);
        System.out.println("排完序的堆的数组结构:" + Arrays.toString(a));

    }
}
