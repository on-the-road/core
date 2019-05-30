package com.example.core.algorithem;

import java.util.Random;

/**
 * @Author wangwei
 * @Date 2019/2/1 15:07
 * -描述- 题目：我有40亿个整数，再给一个新的整数，我需要判断新的整数是否在40亿个整数中，你会怎么做
 * Bitmap -- 著名的大数据位图算法
 * 解析--
 * java中一个int是4个字节，一个字节是8位，所以一个字节是32，(int的取值范围是 -2的31次方 --- 2的31次方 - 1)
 * 2的32次方，所能表示的无符号最大整数是4294967296,就是42亿多个数
 * 每8位转化成一个byte,byte[]的长度就是2的29次方
 * 每个bit位转换为10进制对应一个int数值，32个bit位就能表示42亿多个数
 * bit[]是虚拟的，byte[]是实际用来存储数据的
 *
 */
public class Bitmap {
    //定义一个byte数组缓存所有数据的状态,注意这里的写法，1<<29 相当于1*2的29次方
    byte[] data = new byte[1 << 29];

    //拆分组合bit所表示的数据m，用byte[]表示
    public byte[] splitBigData(int num){
        //todo 获取数值num对应bit数组的索引？这里不是很好理解，分析一下
        //int取值范围 -2的31次方 --- 2的31次方减1(即-2147483648—0—2147483647)，索引嘛，都是非负数
        //所以当num = -2147483647时index=0,当num=0时index=2147483647+1===2的31次方
        //这里的最左侧负数最小值是虚拟bit[]数组的第一个元素下标是0
        //此处的lL不能省略后面的L
        long bitIndex = num + (1l << 31);

        //todo 获取bit数组(虚拟)在byte[]数组中的索引
        //这里可以自己画个图研究下，类型转换时，只保留整数部分，不论后面小数的大小
        //数组下标从0开始,所以这里刚好微妙的吻合要求
        int index = (int) (bitIndex / 8);

        //todo bitIndex在数组byte[]索引index中的具体位置,怎么计算?
        int realIndex = index % 8;  //这里可以理解为数值byte[index]真实的8位bit位中的那一位

        //todo 引入位运算将byte[]数组索引为index的值的各个位按权值相加，不理解？
        //首先data[index] = 0(默认值),初始化状态z,所以现在要给其赋值
        //上一步骤中计算出bitIndex在数值byte[index]所表示的二进制形式中的那一bit位
        //java中或运算符‘|’有一个为1，则为1，否则都是0
        data[index] = (byte) (data[index] | (1 << realIndex));
        return data;
    }

    //输出数组中的数据
    private void output(byte[] bytes){
        int count = 0;
        //循环整个byte[]
        for(int i = 0; i < bytes.length; i++){
            //循环每一个byte[i]中的每一个bit，每一个byte[i]中有8个bit
            for(int j = 0; j < 8; j++){
                //todo 这里不好理解，与‘&’运算符，都为1时则为1
                if(!( ((bytes[i]) & (1 << j)) == 0 ) ){
                    count++;
                    //这里的仔细思考哦
                    int number =  (int)( ((long) i * 8 + j) - (1l << 31));
                    System.out.println("取出的第" + count + "个数：" + number);
                }
            }
        }
    }

    public static void main(String[] args) {
        //验证类型转换问题
        /*System.out.println("------" + 12/7);
        System.out.println("------" + (int)5.9f);*/

        Bitmap bm = new Bitmap();
        Random random = new Random();
        byte[] bytes = null;
        for(int i = 0; i < 3; i++){
            int num = random.nextInt(100);
            System.out.println("读取了第"+ (i + 1) + "个数：" + num);
            //todo 修改bit位状态 -- 理解生成的随机数虚拟化存储到byte[]中
            bytes = bm.splitBigData(num);
        }
        System.out.println("");
        //todo 遍历打印
        bm.output(bytes);
    }
 }
