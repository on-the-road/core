package com.example.core.thread;

/**
 * @Author wangwei
 * @Date 2019/4/10 18:04
 * -描述- 设计自己的线程池
 */
public class SelfThreadPool {
    /**
     * Java线程池的完整构造函数
             public ThreadPoolExecutor(
             int corePoolSize, // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
             int maximumPoolSize, // 线程数的上限
             long keepAliveTime, TimeUnit unit, // 超过corePoolSize的线程的idle时长，
             // 超过这个时间，多余的线程会被回收。
             BlockingQueue<Runnable> workQueue, // 任务的排队队列
             ThreadFactory threadFactory, // 新线程的产生方式
             RejectedExecutionHandler handler) // 拒绝策略
     */

    /** //todo corePoolSize和maximumPoolSize设置不当会影响效率，甚至耗尽线程
     *  //todo workQueue设置不当容易导致OOM
     *  //todo handler设置不当会导致提交任务时抛出异常
     *
     *  //todo 线程池工作顺序
     *  corePoolSize -> 任务队列 -> maximumPoolSize -> 拒绝策略
     *
     *  //todo 可以向线程池提交的任务有两种：Runnable和Callable，二者的区别如下：
     *   方法签名不同，void Runnable.run(), V Callable.call() throws Exception
         是否允许有返回值，Callable允许有返回值
         是否允许抛出异常，Callable允许抛出异常。
         Callable是JDK1.5时加入的接口，作为Runnable的一种补充，允许有返回值，允许抛出异常
     *
     *   //todo 三种提交任务的方式
     *   Future<T> submit( Callabel<T> task )  -- 关心结果
     *   void execute( Runnable command )  -- 不关心结果
     *   Future<?> submit( Runnable task) -- 不关心结果，虽然返回了Future，但是其get方法总是返回null
     *
     *   //todo 避免使用无界队列
        不要使用Executors.newXXXThreadPool()快捷方法创建线程池，因为这种方式会使用无界的任务队列
        为避免OOM，我们应该使用ThreadPoolExecutor的构造方法手动指定队列的最大长度
     *
     *  Example --
     *      ExecutorService executorService = new ThreadPoolExecutor(2, 2,
     *            0, TimeUnit.SECONDS,
     *            new ArrayBlockingQueue<>(512), // 使用有界队列，避免OOM
     *            new ThreadPoolExecutor.DiscardPolicy());
     *
     *  //todo 明确- 拒绝任务时的行为 RejectedExecutionHandler为我们提供了几种拒绝策略
     *      AbortPolicy -- 抛出RejectedExecutionException
     *      DiscardPolicy -- 什么也不做，直接忽略
     *      DiscardOldestPolicy -- 丢弃执行队列中最老的任务，尝试为当前提交的任务提供位置
     *      CallerRunsPolicy -- 直接由，提交任务者执行这个任务
     *      线程池默认的策略是AbortPolicy
     *
     *  //todo 正确构造线程池
     *  正确构造线程池
         int poolSize = Runtime.getRuntime().availableProcessors() * 2;
         BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
         RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
         executorService = new ThreadPoolExecutor(poolSize, poolSize,
         0, TimeUnit.SECONDS,queue,policy);
     *
     * //todo 获取执行结果
     *  调用Future.get()方法能够阻塞等待执行结果
     *  如果向线程池提交了多个任务，要获取这些任务的执行结果，可以依次调用Future.get()获得
     *  但对于这种场景，我们更应该使用ExecutorCompletionService，该类的take()方法总是阻塞等待某一个任务完成
     *  然后返回该任务的Future对象。向CompletionService批量提交任务后
     *  只需调用相同次数的CompletionService.take()方法，就能获取所有任务的执行结果
     *  获取顺序是任意的，取决于任务的完成顺序
     *
     *  //todo Example
     *  void solve( Executor executor, Collection<Callable<Result>> solvers )
             throws InterruptedException, ExecutionException {
             CompletionService<Result> ecs = new ExecutorCompletionService<Result>(executor);// 构造器
             for (Callable<Result> s : solvers)// 提交所有任务
             ecs.submit(s);

             int n = solvers.size();
             for (int i = 0; i < n; ++i) {// 获取每一个完成的任务
             Result r = ecs.take().get();
             if (r != null)
             use(r);
             }
         }
     *
     */


}
