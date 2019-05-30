package com.example.core.algorithem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2019/2/22 16:25
 * -描述- 二叉堆(最大堆) 代码实现
 * - 构建二叉堆的时间复杂度是 O(n)
 *
 */
public class MaxBinaryHeap<T extends Comparable<T>> {
    private List<T> heap; //堆 -- 动态数组 ArrayList

    public MaxBinaryHeap(){
        this.heap = new ArrayList<>();
    }
    /**
      * 最大堆的向上调整算法
      * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
      *     索引为N的节点，其父节点的索引为 (N-1)/2
      * 参数说明：
      *   start -- 被上调节点的起始位置(数组的末尾位置，即最后一个元素的索引)
     *          从start开始向上调，直到0
     */
    protected void filterUp(int start){
        int cur = start; //当前节点的索引
        int p = (cur - 1)/2; //父节点的索引
        T temp = heap.get(cur); //当前节点的value

        while (cur > 0){
            int cmp = heap.get(p).compareTo(temp);
            if(cmp >= 0){ //当前节点值小于父节点值
                break;
            }else { //当前节点值大于等于父节点值
                heap.set(cur,heap.get(p)); //父节点的值替换当前节点的值
                cur = p; //更新当前节点索引
                p = (p - 1)/2; //更新父节点索引
                //当前节点的值一直存在temp中没有动

            }
        }
        //最终，当前索引计算完毕，赋值
        heap.set(cur,temp);
    }

    /**
     * 将 data插入到二叉堆中
     */
    public void insert(T data){
        int size = heap.size();
        //将data插入到表尾
        heap.add(data);
        //向上调整堆,索引从0开始，所以这里传入的data的索引就是插入data之前数组的size
        filterUp(size);
    }

    /**
     * 删除最大堆中的data，返回值 0 -success,1- failed
     */

    public int remove(T data){
        if(heap.isEmpty()) return -1;  //堆是空的
        //获取data在堆中的索引
        int index = heap.indexOf(data);
        if(index == -1) return index;

        int size = heap.size();

        heap.set(index,heap.get(size - 1)); //用末尾元素覆盖要删除的元素
        heap.remove(size - 1); //删除末尾元素

        if(heap.size() > 1) {
            filterDown(index,heap.size() - 1);  //从index位置开始自上向下调整为最大堆
        }
        return 0;
    }

    /**
     * 最大堆的向下调整算法
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *     索引为N的节点，其父节点的索引为 (N-1)/2
     * 参数说明：
     *   start -- 被下调节点的起始位置
     *   end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    protected void filterDown(int start, int end) {
        int cur = start; //当前节点的索引
        int left = 2 * cur + 1; //当前节点的左孩子的索引
        T temp = heap.get(cur); //当前节点的value

        while (left <= end){
            //左孩子与右孩子的值进行比较
            int cmp = heap.get(left).compareTo(heap.get(left + 1));
            if(end > 1 && cmp < 0){
                //左右两个孩子当中选择较大者
                left++;
            }
            //与最大值进行比较
            cmp = temp.compareTo(heap.get(left));
            if(cmp >= 0){
                break;
            }else {
                heap.set(cur,heap.get(left));
                cur = left;
                left = 2 * left + 1;
            }
        }
        //最终，当前索引计算完毕，赋值
        heap.set(cur,temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < heap.size(); i++){
            sb.append(heap.get(i) + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int temp;
        //int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        int a[] = {10, 40};
        MaxBinaryHeap<Integer> tree=new MaxBinaryHeap<>();
        System.out.println("依次添加: ");
        for(int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
            tree.insert(a[i]);
        }
       /* System.out.println();
        System.out.println("最大堆:" + tree );
        temp=85;
        tree.insert(temp);
        System.out.println("添加元素:" + temp);
        System.out.println("最大堆:" + tree);*/
        temp=40;
        tree.remove(temp);
        System.out.println("删除元素:" + temp);
        System.out.println("最大堆:" +  tree);

        temp=10;
        tree.remove(temp);
        System.out.println("删除元素:" + temp);
        System.out.println("最大堆:" +  tree);
    }
}
