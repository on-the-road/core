package com.example.core.algorithem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2019/2/1 13:50
 * -描述-题目 -- 时间复杂度 O(1)
 * 题目：我现在需要实现一个栈，这个栈除了可以进行普通的push、pop操作以外，还可以进行getMin的操作，
 * getMin方法被调用后，会返回当前栈的最小值，你会怎么做呢？你可以假设栈里面存的都是int整数
 */
public class MinStack {
    //借助一个辅助栈来存数据栈中的最小值，以及优化
    private List<Integer> data = new ArrayList<>();
    private List<Integer> mins = new ArrayList<>();

    public void push(int num ) throws Exception{
        data.add(num);
        if(mins.size() == 0){
            mins.add(0);
        }else{
            //辅助栈，mins push最小值的索引
            int min = getMin();
            if(num < min){
                mins.add(data.size() - 1);
            }
        }
    }

    public int pop()throws Exception{
        //栈空，抛出异常
        if(data.size() == 0){
            throw new Exception("栈为空...");
        }
        //pop时先获取索引
        int popIndex = data.size() - 1;
        //获取mins栈顶元素，他是最小值索引
        int minIndex = mins.get(mins.size() - 1);
        //如果pop出去的值的索引是最小值索引，mins出栈
        if(popIndex == minIndex){
            mins.remove(mins.size() - 1);
        }
        return  data.remove(data.size() - 1);
    }

    public int getMin() throws Exception{
        if(data.size() == 0){
            throw new Exception("栈为空...");
        }
        int index = mins.get(mins.size() - 1);
        return data.get(index);
    }
}
