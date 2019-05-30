package com.example.core.SwordOffer;

/**
 * @Author wangwei
 * @Date 2019/3/29 14:11
 * -描述-  给定一个二维数组，其每一行从左到右递增排序
 *        从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中
 * -要求- 时间复杂度 O(M + N)，空间复杂度 O(1)
 *
 * -思路- 该二维数组中的一个数，它左边的数都比它小，下边的数都比它大
 *        因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间
 *        当前元素的查找区间为左下角的所有元素
 */
public class Fourth {
    public boolean find(int target, int [][] matrix){
        //本题中需要判断一下：matrix[0].length == 0，现实中二维数组的第一个元素的长度可以是0！
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int row = matrix.length; //一维的长度
        int cols = matrix[0].length; //二维的长度
        //从右上角开始 -- row = 0 、cols = cols - 1
        int r = 0,c = cols - 1;
        while (r <= row -1 && c >= 0){
            if(target == matrix[r][c]){
                return true;
            }else if(target > matrix[r][c]){
                r++;
            }else {
                c--;
            }
        }
        return false;
    }
}
