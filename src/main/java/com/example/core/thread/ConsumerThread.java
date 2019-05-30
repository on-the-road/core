package com.example.core.thread;

import java.util.Queue;

/**
 * @Author wangwei
 * @Date 2018/11/8 16:20
 * -描述-
 */
public class ConsumerThread extends Thread {
    private final Queue sharedQueue;

    public ConsumerThread(Queue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    /**
     * 这里notify()和notifyAll()的区别 !!!!!
     * notify() -- 唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程
     * notifyAll() -- 唤醒在此对象监视器上等待的所有线程。
     */

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            synchronized (sharedQueue){
                while (sharedQueue.size() == 0){
                    System.out.println("队列为空，等待生产!");
                    try {
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object num = sharedQueue.poll();
                System.out.println("进行消费：" + num);
                sharedQueue.notify();
            }
        }
    }
}
