package com.example.core.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Authon wangwei
 * @Date 2018/10/8 14:34
 * -描述- JMH--微基准测试
 * 比较几种 Random的性能
 */

//基准测试类型
@BenchmarkMode({Mode.AverageTime})
//指定时间单位
@OutputTimeUnit(TimeUnit.NANOSECONDS)
//进行基准测试前需要进行预热,预热的一些参数设置，同@Measurement，但用于预热阶段
@Warmup(iterations = 3, time = 5)
//度量，提供真正的测试阶段参数，iterations进行测试的轮次，time每轮进行的时长，timeUnit时长单位
@Measurement(iterations = 3, time = 5)
//该测试使用的线程数
@Threads(50)
//需要运行的试验(迭代集合)数量。每个试验运行在单独的JVM进程中
@Fork(1)
//指定类实例的可用范围，Scope.Benchmark 运行相同测试的所有线程将共享实例。可以用来测试状态对象的多线程性能
@State(Scope.Benchmark)
public class RandomBenchmark {

    Random random = new Random();

    //Random::new 这是什么写法？java8新特性，method reference方法引用
    //这里的Random::new 并不是 new Random()  *****
    //这一这里的 TreadLocal.withInital(Supplier<? extends S> supplier),参数接收的是一个函数式接口,而不是一个具体的什么类型
    ThreadLocal<Random> threadLocalRandomHolder = ThreadLocal.withInitial(Random::new);

    @Benchmark
    public int random() {
        return random.nextInt();

    }

    @Benchmark
    public int threadLocalRandom() {
        return ThreadLocalRandom.current().nextInt();
    }

    @Benchmark
    public int threadLocalRandomHolder() {
        return threadLocalRandomHolder.get().nextInt();
    }

  /*  @Benchmark
    public int nettyThreadLocalRandom() {
        return io.netty.util.internal.ThreadLocalRandom.current().nextInt();
    }*/

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().
                include(RandomBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
