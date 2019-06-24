package com.example.core.Exception;

/**
 * @Author wangwei
 * @Date 2019/6/17 15:55
 * -描述- todo 测试 一个方法内部有try --catch块，发生异常被catch住之后，try--catch块之后的代码继续执行
 */
public class ContinueAfterCatchException {
    public static void main(String[] args) {
        int a = 1,b = 0;
        try {
            System.out.println(a/b);
        }catch (ArithmeticException ae){
            ae.printStackTrace();
        }
        System.out.println("我被执行了....");
    }
}
