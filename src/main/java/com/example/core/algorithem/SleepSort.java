package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2018/11/19 10:58
 * -描述- 传说中睡眠排序法
  */
public class SleepSort implements Runnable {
    private String num;
    public SleepSort(int num ){
        this.num = num + "";
    }

    //重写线程的run方法
    @Override
    public void run() {
        try {
            Thread.sleep(Integer.parseInt(num));
            System.out.println(num);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] nums = {12,34,6784,9,346,112,23456};
        for (int i = 0; i < nums.length; i++){
            new Thread(new SleepSort(nums[i])).start();
        }
    }

}
