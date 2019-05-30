package com.example.core.thread;

import java.util.concurrent.Semaphore;

/**
 * @Author wangwei
 * @Date 2019/3/14 11:24
 * -描述- 关于线程的两个题目
 */
public class ThreadControll {
    /**
     * 两个线程交替打印 0-100的奇偶数
     * 偶线程：0
     * 奇线程：1
     * 偶线程：2
     * 奇线程：3
     * ....
     */
    static class Task1 implements Runnable{
        //这里必须是静态变量,两个线程共享
        static int value = 0;
        @Override
        public void run() {
            while (value <= 100){
                synchronized (Task1.class){
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    //value++;
                    Task1.class.notify();
                    try {
                        Task1.class.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *通过N个线程顺序循环打印0-100，如果n=3
     * thread0: 0
     * thread1: 1
     * thread2: 2
     * thread0: 3
     * thread1: 4
     * thread2: 5
     * ....
     */
    static int result = 0;
    public static void main(String[] args){

        int N = 3;
        Thread [] threads = new Thread[N]; //存放线程的数组
        final Semaphore [] semaphores = new Semaphore[N]; //信号量数组
        for (int i = 0; i < N; i++){
            //每个semaphore 初始化一个许可
            semaphores[i] = new Semaphore(1);
            if(i != N - 1){ //不是最后一个
                //semaphore.acquire():获取到许可,这里控制最后一个不能获取许可
                //每个acquire方法阻塞，直到有一个许可证可以获得然后拿走一个许可证
                try {
                    semaphores[i].acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < N; i++){
            //最后一个信号量
            final Semaphore lastSemaphore = i == 0 ? semaphores[N - 1] : semaphores[i - 1];
            //当前信号量
            final Semaphore currentSemaphore = semaphores[i];
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            //获取许可证
                            lastSemaphore.acquire();
                            System.out.println("thread" + index + ":" + result++);
                            if(result > 100) System.exit(0);
                            //当前信号量释放许可
                            //每个release方法增加一个许可证，这可能会释放一个阻塞的acquire方法
                            currentSemaphore.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threads[i].start();
        }
      }

   /* public static void main(String[] args) {
        new Thread(new Task1(),"偶线程：").start();
        new Thread(new Task1(),"奇线程：").start();
    }*/


}
