package com.example.core.algorithem;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/8/19.
 *
 * 各种排序算法
 */
public class Sort {
    //冒泡排序
    public  void maopao_sort(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }
        int len = arr.length;
        for(int i = 0; i< len - 1; i++){ //做len - 1次排序
            for(int j = 0; j < len - 1 - i;j++){ //交换元素
                if(arr[j] < arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public  static void    quick_sort(int[] arr,int start,int end){
        if(start >= end) return;
        int i = start;
        int j = end;
        int value = arr[i];
        boolean flag = true; //这里开始设置为true
        while (i != j){ //这里的条件 不能乱
            if(flag){
                if(value > arr[j]){ //从后往前比较
                    _swap(arr,i,j);
                    flag = false;
                }else{
                    j--;
                }
            }else{
                if(value < arr[i]){ //从前完后比较
                    _swap(arr,i,j);
                    flag = true;
                }else{
                    i++;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        //这里i = j 所以 用i 和 j是一样的
        quick_sort(arr,start,i-1);
        quick_sort(arr,i+1,end);
    }

    private static void _swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 二分查找算法
     * @param
     */
    public static int  binarySearch(int[] arr, int key){
        int low =0;
        int high = arr.length - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if(arr[mid] == key){
                return mid;
            }else if(arr[mid] > key){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return  -1;
    }

    /**
     * 字符串 反序
     * @param str
     */

    public static String reverse(String str) {
        if((null== str) || (str.length() <= 1)) {
            return str;
        }
        //return  new StringBuffer(str).reverse().toString();
        StringBuffer reverse = new StringBuffer(str.length());
        for(int i = str.length() - 1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        return reverse.toString();
    }

    /**
     * 给定一个字符串 反序输出  用递归实现
     * @param str
     */

    public static String _reverse(String str) {
        if(str == null || str.length() <= 1) { return str;}
        return _reverse(str.substring(1)) + str.charAt(0);
        //return str.charAt(0) + _reverse(str.substring(1));
    }

    public static void main(String[] args) {
        int arr[] = {32,13,78,28,9,45,31,28,29};
        quick_sort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr,28));
        System.out.println(_reverse("wangwei"));
    }

    /**
     * 给定一个包含n个无序的int类型整数的数组arr，并且这些整数的取值范围都在
     * 0-20 之间
     * 要求在 O(n) 的时间复杂度把这n个数按照从小到大的顺序打印出来
     * 思路 --- 巧用数组下标
     * @param arr
     */

    public static void printNum(int[] arr) {
        int[] temp = new int[21];
        for (int i = 0; i < arr.length; i++) { //时间复杂度O(n)
            temp[arr[i]]++;
        }
        //顺序打印
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < temp[i]; j++) {
                System.out.print(i);
            }
        }
    }

    // 递归实现方式
    public int fibonacci(int n) {
        if (n <= 2) {
            return n;
        } else {
            return fibonacci(n - 2) + fibonacci(n - 1);
        }
    }

    // 递推实现方式
    public static int fibonacciNormal(int n) {
        if (n <= 2) {
            return n;
        }
        int n1 = 1, n2 = 2, sn = 0;
        for (int i = 0; i < n - 2; i++) {
            sn = n1 + n2;
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }

    /**
     * 自幂数介绍:
     * 一位自幂数：独身数
     * 两位自幂数：没有
     * 三位自幂数：水仙花数
     * 四位自幂数：四叶玫瑰数
     * 五位自幂数：五角星数
     * 六位自幂数：六合数
     * 七位自幂数：北斗七星数
     * 八位自幂数：八仙数
     * 九位自幂数：九九重阳数
     * 十位自幂数：十全十美数
     * 输出所有的水仙花数，所谓水仙花数是指一个3位数，其每位上数字的立方和等于其本身，
     * 例如： 153 = 1*1*1 + 3*3*3 + 5*5*5
     * int number = 100; //范围是 100--999
     * int i,j,k; //分为是number的百位，十位，个位
     * for(int sum; number < 1000; number ++)
     * {
     * i = number/100;
     * j = ( number - i * 100)%10;
     * k = number%10;
     * if( number == i*i*i + j*j*j + k*k*k ){
     * System.out.print(number);
     * }
     * }
     *
     * 通用解法：------------
     * Math.pow(double a,double b) -- 求a的b次幂(a的b次方)
     * Math.sqrt(double a) -- a的开方 (根号a)
     */
    public static void perfectResolver(int n) {
        for (int value = (int) Math.pow(10, n - 1); value < Math.pow(10, n); value++) {
            int num = 0;
            for (int i = 0; i < n; i++) {
                int temp = (int) (value / Math.pow(10, i)) % 10;
                num += Math.pow(temp, 3);
            }
            if (value == num) {
                System.out.println(value);
            }
        }

        //这里注意Math.pow(a,b)的返回类型是double
        for(int need = (int) Math.pow(10,n - 1); need < Math.pow(10,n); need++){
            int sum = 0;
            for(int i = 0; i < n; i++){
                int temp = (int) ((need /Math.pow(10,i)) % 10);
                sum += temp;
            }
            if(sum == need){
                System.out.println("要找的数据：" + need);
            }
        }
    }

    /**
     * 题目：有十堆金币，有一堆是假的，其余是真的，假的每个都比真的少一克，
     * 用电子秤称一次，怎么分出真假。
     * 答案：分别拿一个两个三个....十个出来。差多少克就是哪堆
     * <p>
     * 题目：小明一家过一座桥，过桥时是黑夜，所以必须有灯。现在小明过桥要1秒，小明的弟弟要3秒，小明的爸爸要6秒，小明的妈妈要8秒，小明的爷爷要12秒。每次此桥最多可过两人，而过桥的速度依过桥最慢者而定，而且灯在点燃后30秒就会熄灭。问：小明一家如何过桥？
     * 小明 + 弟弟 过去 ——3秒
     * 小明 回来—— 1秒
     * 小明 + 爸爸 过去 —— 6秒
     * 小明 回来 —— 1秒
     * 爷爷 + 妈妈 过去 —— 12秒
     * 弟弟 回来 —— 3秒
     * 弟弟 + 小明 过去 —— 3秒
     * 一共 29秒，巧妙的方法吧？小明 + 弟弟 过去 ——3秒
     * <p>
     * 题目：n级台阶，每次可以走一级或是2级，一共有多少种走法
     * 本质 -- 斐波那契序列问题
     */

    public int all(int a) {
        if (a <= 0) return 0 ;
        if (a == 1 || a == 2) return a;
        return all(a - 1) + all(a - 2);
    }
}


/**
 * Created by Administrator on 2017/8/20.
 * java实现阻塞队列 -- test
 */
class BlockingQueue2 {
    private int size;
    final Lock lock;
    final Condition notFull; //队列满时的阻塞锁
    final Condition notEmpty; //队列空时的阻塞锁
    final Object[] item;
    int putIndex,takeIndex,count;

    public BlockingQueue2(int size){
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
        this.item = new Object[size];
    }

    public void put(Object x) throws Exception{
        System.out.println("进入 --- put");
        lock.lock();
        try{
            while (count == item.length){
                notFull.await();
            }
            item[putIndex] = x;
            ++count;
            if(++putIndex == item.length){
                putIndex = 0;
            }
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public Object take()throws Exception{
        System.out.println("进入 --- take");
        lock.lock();
        try{
            while (count == 0){
                notEmpty.await();
            }
            Object x = item[takeIndex];
            item[takeIndex] = null;
            --count;
            if(++takeIndex == item.length){
                takeIndex = 0;
            }
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }

    /**
     * synchronized实现
     */
    final Object notFu = new Object();
    final Object notEm = new Object();

    public void put_old(Object x)throws  Exception{
        synchronized (notFu){
            while(count == item.length){
                notFu.wait();
            }
            item[putIndex] = x;
            ++count;
            if(++putIndex == item.length){
                putIndex = 0;
            }
        }
        synchronized (notEm){
            notEm.notify();
        }
    }

    public Object take_old()throws Exception{
        Object x;
        synchronized (notEm){
            while(count == 0){
                notEm.wait();
            }
            x = item[takeIndex];
            item[takeIndex] = null;
            --count;
            if(++takeIndex == item.length){
                takeIndex = 0;
            }
        }
        synchronized (notFu){
            notFu.notify();
        }
        return x;
    }

    public boolean isEmpty(){
        if(item.length <= 0){
            return true;
        }
        return false;
    }


    /**
     * 怎么测试阻塞队列的正确性?
     */

    public static void main(String[] args) throws Exception{
        BlockingQueue2 bq2 = new BlockingQueue2(2);

        new Thread(()->{
            while (!bq2.isEmpty()){
                try {
                    System.out.println("队列获取到的数据为:" + bq2.take_old());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"Reader").start();

        bq2.put_old("12");
        bq2.put_old("123");
        bq2.put_old("1234");
        TimeUnit.SECONDS.sleep(100);
    }

}







