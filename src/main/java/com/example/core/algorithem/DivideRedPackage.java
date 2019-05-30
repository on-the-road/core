package com.example.core.algorithem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author wangwei
 * @Date 2018/11/20 18:26
 * -描述- 抢红包算法
 *  剩余区间 （ 0 , m/n * 2 ]
 */
public class DivideRedPackage {
    //发红包算法，金额以分为单位

    /** 1. 二倍均值法
     * 这是很平均的一种算法，除了最后一次，任何一次抢到的金额都要小于人均金额的两倍
     * 并不是任意的随机
     * key -- 剩余的金额/剩余的人数 * 2
     * @param totalAmount
     * @param totalPeople
     * @return
     */
    public static List<Integer> dividePackage(Integer totalAmount, Integer totalPeople){
        List<Integer> amountList = new ArrayList<>();
        Integer restPeople = totalPeople;
        Integer restAmount = totalAmount;
        Random random = new Random();

        for(int i = 0; i < totalPeople - 1; i++){ //这里的restPeople-1余下的一人不用抢？
            int amount = random.nextInt(restAmount/restPeople * 2 -1 ) + 1;
            restPeople --;
            restAmount -= amount;
            amountList.add(amount);
        }
        //最后一个人的红包，加到集合中
        amountList.add(restAmount);
        return  amountList;
    }

    /**
     * 2.线性切割法
     * 我们可以把红包总金额想象成一条很长的线段，而每个人抢到的金额，则是这条主线段所拆分出的若干子线段
     * 如何确定每一条子线段的长度呢？由“切割点”来决定。当N个人一起抢红包的时候，就需要确定N-1个切割点
     *
     * @param args
     */

    public static void main(String[] args) {
        List<Integer> tarList = dividePackage(10000,10);
        for (int i = 0; i < tarList.size(); i++){
            System.out.println("第" +(i + 1) + "个人抢到：" + tarList.get(i)/100+ "元。");
        }
    }
}
