package com.example.core.algorithem;

/**
 * -描述-
 * 寻找无序数组的第K大元素
 * 使用二叉堆最小堆实现
 * 构建堆的时间复杂度是O(k)
 * 遍历剩余数组的时间复杂度是O(n-k)
 * 每次调整堆的时间复杂度是O(logk)
 * 总体的时间复杂度 === O( (n-k)logk + k)   ===> nlogk
 */
public class TheKthBigNum {
    /**
     * 构建最小堆
     * @param array -- 待操作的数组
     * @param length -- 数组的长度
     */
    public static void makeHeap(int[] array,int length){
        //从最后一个非叶子节点开始，依次进行下沉调整
        //一个数组就可以看成是一个堆，只不过不符合堆的规则，需要进行调整
        for (int i = (length - 2)/2; i >= 0; i--){
            adjustNode(array,i,length);
        }
    }

    /**
     * 调整堆
     * @param array
     * @param i  -- 需要下沉的节点
     * @param length
     */
    private static void adjustNode(int[] array, int i, int length) {
        int temp = array[i];   //这是父节点
        //数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)
        //右孩子的索引是(2N+2),索引为N的节点，其父节点的索引为 (N-1)/2
        int leftIndex = 2 * i  + 1;  //右孩子的索引是  leftIndex + 1
        while (leftIndex < length){ //说明有左孩子
            //如果有右孩子，并且右孩子小于最孩子，则与右孩子比较
            if(leftIndex + 1 < length && array[leftIndex + 1] < array[leftIndex]){
                leftIndex++; //这一步操作使下面的leftIndex变成右孩子..
            }
            if(array[i] <= array[leftIndex]) break;
            //无需真正的交换，单向赋值即可,配合一张图看更好理解
            array[i] = array[leftIndex];
            i = leftIndex;
            leftIndex = 2* leftIndex + 1;
        }
        array[i] = temp;
    }

    /**
     * @param array  -- 无序数组
     * @param k     -- 第k大元素
     */
    public static int findKthNum(int[] array, int k){
        //用前k个元素构建一个最小堆
        makeHeap(array,k);
        //遍历数组,与堆顶比较（此时堆顶元素时最小的），大的直接替换堆顶
        for (int i = k; i < array.length; i++){
            if(array[i] > array[0]){
                array[0] = array[i];
                //调整
                adjustNode(array,0,k);
            }
        }
        //返回堆顶元素 -- 既是第k大元素
        return array[0];
    }

    public static void main(String[] args) {
        int[] arr = {23,4,12,45,1,67,38,31,8,5,16,20,47};
        //寻找这个数组中第4大的元素
        System.out.println(findKthNum(arr,4));
    }
}
