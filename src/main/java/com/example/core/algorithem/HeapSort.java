package com.example.core.algorithem;

import java.util.Arrays;

/**
 * -描述- 堆排序,非稳定排序，时间复杂度稳定在O(nlogn),空间复杂度O(1)
 * 这里说明一下：快速排序是平均复杂O (nlogn)
 * 实际上，快速排序的最坏时间复杂度是O(n^2)而像归并排序，堆排序，都稳定在O(nlogn)
 *      最大堆 -- 升序排序
 *      最小堆 -- 降序排序
 *      构建二叉堆 --
 *      要把一个无序的完全二叉树调整为二叉堆，我们可以让所有非叶子节点依次下沉
 *      不过下沉的顺序不是从根节点开始下沉，而是从下面的非叶子节点下沉
 *  注 -- 数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)
 *        索引为N的节点，其父节点的索引为 floor( (N-1)/2 )
 *
 *   二叉堆的构建过程 --
 *      给定一个数组直接按顺序排成二叉树
 *      调整--从最后的非叶子节点开始调整
 *   二叉堆的插入过程
 *  1.把要插入的节点放在二叉堆的最末端
 *  2.把这个元素和它的父节点进行比较，如果符合条件或者该节点已是头结点插入操作就算完成了
 *  3.如果不符合条件的话就交换该节点和父节点位置。并跳到第二步
 */
public class HeapSort {
    /**
     * 最大堆通常被用来进行"升序"排序
     * @param a -- 待排序数组
     * @param n -- 数组长度
     */
    public void heap_sort_asc(int[] a, int n){
        /** 给定一个数组，构建成一个最大二叉堆
            从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆
            数组长度为n，索引从0开始 -- >最大父节点索引：n/2 - 1
            推导过程： 索引从0开始，索引为i的父节点索引是 floor((i-1)/2)
            所以，索引为n-1的节点(数组的末尾元素)的父节点的索引是： floor((n - 1 -1)/2) ==> n/2 -1!
         */
        //把给定的数组构建成一个二叉堆
        for(int i = n/2 -1; i >= 0; i--){
            //最大堆下沉,从(n/2-1) --> 0逐次遍历,每次遍历的都是父节点
            max_heap_down(a,i,(n - 1));
        }

        System.out.println("构建的大根堆：" + Arrays.toString(a));
        //从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        //每次窃取最大堆的堆顶元素，放到数组的末尾，把窃取之后的堆再次调整为最大堆
        for(int i = n - 1; i > 0; i--){
            // 交换a[0]和a[i]
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            //交换后，保证a[0...i-1]任然是一个最大堆
            max_heap_down(a,0, i- 1);
        }
    }

    /**
     * 最大堆的向下调整方法
     * @param a
     * @param start -- 被下调节点的起始位置，一般从0开始
     * @param end -- 截止位置，一般是数组中最后一个元素的索引
     */
    private void max_heap_down(int[] a, int start, int end) {
        int current = start; //当前节点的索引
        int left = 2 * current + 1; //当前节点左孩子的索引
        int temp = a[current]; //当前节点的value
        for(; left <= end; current = left,left = 2 * left + 1){
            //todo 这里第一次遍历, 第三项不做计算。left--是左孩子，left + 1 -- 是右孩子
            if(left < end && a[left] < a[left +1]){
                left++; //相当于left变成了右孩子
            }
            //与较大的进行比较
            if(temp >= a[left]){  //调整结束
                break;
            }else { //交换value
                a[current] = a[left];
                a[left] = temp;
            }
        }
    }

    /**
     * 最小堆通常被用来进行"降序"排序
     * @param a  -- 待排序数组
     * @param n  -- 数组长度
     */
    public void heap_sort_desc(int[] a,int n){
        //把给定数组构建成为一个最小堆
        //从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最小)二叉堆
        for(int i = n/2 - 1; i >= 0; i--){
            //最小堆下沉,从(n/2-1) --> 0逐次遍历,每次遍历的都是父节点
            min_heap_down(a,i,n-1);
        }
        System.out.println("构建的小根堆:" + Arrays.toString(a));
        //从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        //每次窃取最小堆的堆顶元素，放到数组的末尾，把窃取之后的堆再次调整为最小堆
        for(int i = n - 1; i > 0; i--){
            //交换a[0]和a[i]
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            //交换后，保证a[0...i-1]任然是一个最小堆
            min_heap_down(a,0,i-1);
        }
    }

    /**
     * 最小堆的调整方法
     * @param a
     * @param start -- 被下调节点的起始位置，一般从0开始
     * @param end  --  截止位置，一般是数组中最后一个元素的索引
     */
    private void min_heap_down(int[] a, int start, int end) {
        int current = start;
        int left = 2 * current + 1;
        int temp = a[current];
        for (; left <= end; current = left,left = 2 * left + 1){
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

    public static void main(String[] args) {
        //int a[] = {1,8,6,2,5,4,7,3};
        int a[] = {5,11,7,2,3,17};
        HeapSort hs = new HeapSort();
        System.out.println("before-sort:");
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
        //hs.heap_sort_asc(a,a.length);
        hs.heap_sort_asc(a,a.length);
        System.out.println("");
        System.out.println("after-sort:");
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
    }

}
