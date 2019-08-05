package com.example.core.algorithem;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangwei
 * @Date 2019/6/17 14:40
 * -描述- todo 算法中的一些奇淫巧技
 */
public class SmartAlgor {
    public static void main(String[] args) {
       String s = "acca";
       int count = lengthOfLongestSubstring(s);
        System.out.println("最长的子串长度是:" + count);
    }

    //todo 滑动窗口 思路
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), max = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int end=0,start=0; end<n; end++){
            char tar = s.charAt(end);
            if(map.containsKey(tar)){
                start = Math.max(start,map.get(tar));
            }
            max = Math.max(max,end-start+1);
            //todo 加1表示从字符位置后一个才开始不重复,不理解
            map.put(tar,end + 1);
        }
        return max;
    }
}
