package com.example.core.thread;

import java.util.Queue;

/**
 * @Author wangwei
 * @Date 2018/11/8 16:08
 * -描述-
 */
public class ProducerThread extends Thread {
    private static final int MAX_QUEUE_SIZE = 5;

    //这种final修饰的变量，是在构造函数里赋初值
    private final Queue sharedQueue;
    public ProducerThread(Queue sharedQueue){
        super();
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            synchronized (sharedQueue){
                while (sharedQueue.size() >= MAX_QUEUE_SIZE){
                    System.out.println("队列已满，等待消费!");
                    try {
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sharedQueue.add(i);
                System.out.println("进行生产：" + i);
                sharedQueue.notify();
            }
        }
    }
}
