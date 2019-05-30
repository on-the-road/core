package com.example.core.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author wangwei
 * @Date 2018/11/8 16:07
 * -描述-
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Queue<Integer> sharedQueue = new LinkedList<>();
        Thread producer = new ProducerThread(sharedQueue);
        Thread consumer = new ConsumerThread(sharedQueue);
        producer.start();
        consumer.start();
    }
}
