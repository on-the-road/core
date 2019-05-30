package com.example.core.thread;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * @Author wangwei
 * @Date 2019/3/28 10:08
 * -描述- 一道有趣的面试题
 *   --- java程序，主进程需要等待多个子进程结束之后再执行后续的代码，有哪些方案可以实现？
 */
public class Interview {
    /**
     * 1 .Thread.join()方法
     * thread.join()把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程
     * 比如在线程B中调用了线程A的join()方法，直到线程A执行完毕后，才会继续执行线程B
     */

    public static void testJoin()throws Exception{
        Vector<Thread> vector = new Vector<>();
        for (int i = 0; i < 5; i++) {
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                }
            });
            vector.add(childThread);
            childThread.start();
        }
        for (Thread thread: vector){
            thread.join();
        }
        System.out.println("主线程被执行");
    }

    /**
     * 2. CountDownLatch -- 倒计数门闸锁
     *    CountDownLatch是一个同步工具类，用来协调多个线程之间的同步
     *    或者说起到线程之间的通信（而不是用作互斥的作用）
     *    CountDownLatch能够使一个线程在等待另外一些线程完成工作之后，再继续执行
     *    使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后
     *    计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成了任务
     *    然后在CountDownLatch上等待的线程就可以恢复执行
     *    典型用法：1.某一线程在开始运行前等待n个线程执行完毕
     *              2.实现多个线程开始执行任务的最大并行性
     *    CountDownLatch是一次性的，计数器的值只能在构造方法中初始化一次
     *    之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用
     */
    public static void testCountDownLatch() throws Exception{
        final CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                    latch.countDown();
                }
            });
            childThread.start();
        }
        latch.await(); //阻塞当前线程，知道countDownLatch的值为0
        System.out.println("主线程被执行");
    }

    /**
     * 3. CyclicBarrier  -- 可重用栅栏，同步屏障
     *    CylicBarrier是控制一组线程的同步,它允许一组线程互相等待，直到到达某个公共屏障点
     *    这个公共点可以理解为，组中的所有线程都调用了await()方法，还可以理解为组中其他线程都执行了
     *    操作，并且都调用了await()方法,当前线程才能继续往下执行，一个逻辑上的公共点..
     *    初始化的参数：5的含义是包括主线程在内有5个线程，所以只能有四个子线程
     *    这与CountDownLatch是不一样的
     *    countDownLatch只能使用一次，而CyclicBarrier方法可以使用reset()方法重置
     *    两者区别的形象比喻：
     *    在百米赛跑的比赛中若使用countDownLatch的话冲过终点线一个人就会给评委发送一个人的成绩
     *    10个人比赛发送10次，
     *    如果用CyclicBarrier，则只在最后一个人冲过终点线的时候发送所有人的数据，仅仅发送一次
     */

    public static void testCyclicBarrier()throws Exception{
        final CyclicBarrier barrier = new CyclicBarrier(5); //注意这里的5,是包含主线程的
        for (int i = 0; i <4 ; i++) {
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                    try {
                        barrier.await();//当前线程等待直到所有线程都调用了该屏障的await()方法
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            childThread.start();
        }
        barrier.await(); //阻塞当前线程,当前线程等待直到所有线程都调用了该屏障的await()方法
        System.out.println("主线程被执行");
    }

    /**
     *  CountDownLatch和CyclicBarrier的区别？
     * 　1）CountDownLatch:一个线程(或者多个)，等待另外N个线程完成某个事情之后才能执行
     *   CyclicBarrier:N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待
     *　 2）CountDownLatch:一次性的；CyclicBarrier:可以重复使用。
     *　 3）CountDownLatch基于AQS；CyclicBarrier基于锁和Condition。本质上都是依赖于volatile和CAS实现的
     *
     * 这里说下：Java线程中有一个Thread.yield( )方法，很多人翻译成线程让步
     * 顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，让自己或者其它的线程运行
     * 使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。cpu会从众多的可执行态里选择
     * 也就是说，当前也就是刚刚的那个线程还是有可能会被再次执行到的
     * 并不是说一定会执行其他线程而该线程在下一次中不会执行到了
     */

    /**
     *  4. FutureTast可用于闭锁，类似于CountDownLatch的作用
     */

    public void testFutureTask(){
        MyTask myTask = new MyTask(); //定义自己的线程实现类

        //执行Callback方式，需要Callable实现类的支持
        FutureTask<Integer> future1 = new FutureTask<Integer>(myTask);
        new Thread(future1).start();
        FutureTask<Integer> future2 = new FutureTask<Integer>(myTask);
        new Thread(future2).start();
        FutureTask<Integer> future3 = new FutureTask<Integer>(myTask);
        new Thread(future3).start();
        FutureTask<Integer> future4 = new FutureTask<Integer>(myTask);
        new Thread(future4).start();

        Integer sum;
        try {
            sum = future1.get();
            sum = future2.get();
            sum = future3.get();
            sum = future4.get();
            //这里获取sum值只是为了同步，并没有实际意义,同步的同时阻塞其他线程的执行
            System.out.println(sum);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("主线程被执行了");
    }

    class  MyTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < 10 ; i++) {
                sum += i;
            }
            System.out.println("子线程被执行");
            return sum;
        }
    }



    public static void main(String[] args) throws Exception{
        testJoin();

        //testCountDownLatch();

        //testCyclicBarrier();

        //new Interview().testFutureTask();

    }
}
