package com.example.core.java8;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @Author wangwei
 * @Date 2019/6/13 13:54
 * -描述- todo 并行Stream，并行Stream的秘密就在于，背后使用了通用的并发框架ForkJoinPool (since jdk1.7)
 *        todo 这是通过利用静态方法 ForkJoinPool.commonPool()来实现的
 *        todo 对于ForkJoinPool，其实际使用的线程数取决于机器背后的实际CUP数量
 */
public class StreamParalle {
    public static void main(String[] args) {
        ForkJoinPool commonPoll = ForkJoinPool.commonPool();
        System.out.println(commonPoll.getParallelism());

        /**
         * 在Java8中，Collection支持使用方法parallelStream()方法来直接创建出元素的并行 Stream
         * 此外，你也可以在一个顺序Stream上调用非终结函数parallel()来转换该 Stream为一个并行Stream
         */

        //todo 通过控制台很好的理解到底是哪一个线程在执行并行Stream上的操作
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

        //todo ForkJoinPool不是为了替代ExecutorService，而是它的补充，在某些应用场景下性能比ExecutorService更好
        //todo ForkJoinPool主要用于实现“分而治之”的算法，特别是分治之后递归调用的函数，例如quick sort等
        //todo ForkJoinPool最适合的是计算密集型的任务，如果存在 I/O，线程间同步，sleep()
        //todo 等会造成线程长时间阻塞的情况时，最好配合使用ManagedBlocker
    }
}
