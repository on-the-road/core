package com.example.core;

import com.example.core.spi.SpiInterface;
import com.example.core.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.ServiceLoader;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CoreApplicationTests {

	@Autowired
	private Task task;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testTask() throws Exception{
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();

		//这里 join()的作用
		//“等待该线程终止”，这里需要理解的就是该线程是指的主线程等待子线程的终止。
		// 也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行
		//Thread.currentThread().join();
		System.out.println("Game over!!");
	}

	@Test
	public void testFuture() throws Exception{
		Future<String> future = task.run();
		String result = future.get(5, TimeUnit.SECONDS);
		System.out.println("result------" + result);
	}

	@Test
	public void testDivision() throws Exception{
		System.out.println(12/5.0);
		System.out.println(new Date().getTime());
	}

	//测试spi机制，手动添加目录 /resources/META-INF/services/com.example.core.spi.SpiInterface
	@Test
	public void testSpi() throws Exception{
		ServiceLoader<SpiInterface> loaders = ServiceLoader.load(SpiInterface.class);
		for (SpiInterface si: loaders){
			si.say();
		}
	}

}
