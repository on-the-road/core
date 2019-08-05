package com.example.core.test;

import java.util.*;

/**
 * @Author wangwei
 * @Date 2019/7/23 10:00
 * -描述-
 */
public class TestAll {
    final static ThreadLocal<String> tl = new ThreadLocal<>();




    public static void main(String[] args) {
        String a = "hello";
        System.out.println("测试charAt:" +  (a.charAt(1) - '0'));
        System.out.println("测试charAt:" +  a.charAt(1));

        int n = 5,curr = 0,next = 1;
        while(n-- > 0){
            next = next + curr;
            curr = next - curr;
        }
        System.out.println("--------：" + curr);


        int[] arr = {1,2,4,5,0,0,0,0};
        int[] b = {7,8,9};
        System.arraycopy(b,0,arr,4,3);
        System.out.println(Arrays.toString(arr));

        List<String> list = new ArrayList<>();
        list.add("lili");
        list.add("lucy");
        list.add("leijun");
        String str = "Z";
        for (char c: str.toCharArray()){
            System.out.println((byte)c);
        }

        Queue<String> link = new LinkedList<>();
        link.add("susan");
        link.add("huji");
        link.add("tugu");
        link.remove();
        System.out.println(link.toString());


        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-2).length());
        System.out.println(Integer.lowestOneBit(8));

        Stack<Integer> stack = new Stack<>();


        System.out.println("a的ascii码：" + (byte)'a');
        System.out.println("z的ascii码：" + (byte)'z');



    }



}
