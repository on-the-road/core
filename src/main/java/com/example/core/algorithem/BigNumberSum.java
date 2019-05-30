package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2018/11/19 16:48
 * -描述- 两个大整数求和
 */
public class BigNumberSum {
    public static String sum(String bigNumA,String bigNumB){
        //1.把两个大整数逆序用数组存储,这里记住String类本身是没有reverse()方法的，
        //2、StringBuffer是线程安全的，StringBuilder是线程不安全的
        char[] charsA = new StringBuffer(bigNumA).reverse().toString().toCharArray();
        char[] charsB = new StringBuffer(bigNumB).reverse().toString().toCharArray();

        //3.定义 result数组，设置最大的length
        int maxLen = charsA.length > charsB.length ? charsA.length : charsB.length;
        int [] result = new int[maxLen + 1];

        //4.遍历数组，按位相加
        for (int i = 0; i < result.length; i++){
            //这里有一个关键的知识点，就是 charsA[i] 是一个字符，并非是想当然的数字
            //怎么来解决这个问题,字符的字码与字符本身的数字值，换算就是减一个字符 '0',结果就是字符本身的数值
            int temp = result[i];
            if(i < charsA.length){
                temp += charsA[i] - '0';
            }
            if(i < charsB.length){
                temp += charsB[i] - '0';
            }

            //判断是否需要进位
            if(temp >= 10){
                temp -= 10;
                result[i + 1] = 1;
            }
            //最后给result[i]赋值
            result[i] = temp;

        }
        //把result数组再次逆序并转换成string
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for(int i = result.length - 1; i >= 0; i--){
            //这里的最大位判断是否是零，很巧妙,仔细思考
            if(result[i] == 0 && flag){
                continue;
            }
            flag = false;
            sb.append(result[i]);
        }
        //返回结果
        return  sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(sum("426709752318","95481253129"));

    }
}
