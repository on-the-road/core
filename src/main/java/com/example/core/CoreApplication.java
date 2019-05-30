package com.example.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	/*//自定义线程池
	@EnableAsync  //支持异步调用
	@Configuration
	class TaskPoolConfig{
		@Bean("taskExecutor")
		public Executor taskExecutor (){
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			//设置核心线程数
			executor.setCorePoolSize(5);
			//线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
			executor.setMaxPoolSize(10);
			//缓冲队列，用来缓冲执行任务的队列
			executor.setQueueCapacity(4);
			//允许线程的空闲时间,当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
			executor.setKeepAliveSeconds(60);
			//线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
			executor.setThreadNamePrefix("ThreadPool-Boy");
			//线程池对拒绝任务的处理策略：这里采用了 CallerRunsPolicy策略，
			//当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务
			//如果执行程序已关闭，则会丢弃该任务
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
			//该方法用来设置线程池中任务的等待时间
			executor.setAwaitTerminationSeconds(60);
			//等待所有线程执行完毕,表明等待所有线程执行完
			executor.setWaitForTasksToCompleteOnShutdown(true);
			return executor;
		}

		ExecutorService executor = Executors.newWorkStealingPool();
	}
*/



}
