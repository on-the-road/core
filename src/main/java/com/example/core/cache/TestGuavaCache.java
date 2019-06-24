package com.example.core.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.RunnableEventExecutorAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangwei
 * @Date 2018/11/14 13:31
 * -描述- guava cache 相关练习
 */
public class TestGuavaCache {
   /* public static void main(String[] args) {
        //简单应用
        Cache cache = CacheBuilder.newBuilder().build();
        cache.put("cache","hello world!");
        System.out.println(cache.getIfPresent("cache"));
    }*/

    /*public static void main(String[] args) {
        //设置最大的存储
        Cache cache = CacheBuilder.newBuilder().maximumSize(2).build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }*/

    /*public static void main(String[] args) throws InterruptedException {
        //设置过期时间,对象写入cache3秒后过期
        Cache cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1","expireAfterWrite");
        int time = 1;
        while (true){
            System.out.println("第" + time + "次，获取到key1的值为：" + cache.getIfPresent("key1"));
            Thread.sleep(1000);
            time++;
            if(time == 5) return;
        }

    }*/

    /*public static void main(String[] args) throws InterruptedException {
        //设置过期时间,对象写入cache3秒没有被访问后过期
        Cache cache = CacheBuilder.newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1","expireAfterWrite");
        int time = 1;
        while (true){
            System.out.println("第" + time + "次，获取到key1的值为：" + cache.getIfPresent("key1"));
            time++;
            Thread.sleep(time * 1000);
            if(time == 5) return;
        }

    }*/

    /*public static void main(String[] args) throws InterruptedException {
        //这是移除对象的监听器,可以利用监听器做一些事
        RemovalListener<String,String> listener = new RemovalListener<String, String>(){
            @Override
            public void onRemoval(RemovalNotification<String,String> removalObj) {
                System.out.println("[" + removalObj.getKey() + ":" + removalObj.getValue()+"] is removed!");
            }
        };
        //设置对象移除监听器
        Cache cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .recordStats() //这个方法能收集统计信息
                .removalListener(listener)
                .build();
        cache.put("key1","removalListener-action-1");
        cache.put("key2","removalListener-action-2");
        cache.put("key3","removalListener-action-3");
        cache.put("key4","removalListener-action-4");
        cache.put("key5","removalListener-action-5");

        System.out.println("打印统计信息：" + cache.stats());
    }*/
}

class inner{

    private static Cache cache = CacheBuilder.newBuilder().maximumSize(3).build();

    public static void main(String[] args) {
        /**
         * 测试 cache的自动加载
         * Guava可以保证当有多个线程同时访问Cache中的一个key时，如果key对应的记录不存在，
         * Guava只会启动一个线程执行get方法中Callable参数对应的任务加载数据存到缓存。当加载完数据后，
         * 任何线程中的get方法都会获取到key对应的值
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread-1");
                    try {
                        String value = (String) cache.get("key", new Callable<String>() {
                            public String call() throws Exception {
                                System.out.println("load-Thread-1");
                                Thread.sleep(1000);
                                return "This is Thread-1-data...";
                            }
                        });
                        System.out.println("Threa-1:" + value);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String value = (String) cache.get("key", new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println("load-Thread-2");
                            Thread.sleep(1000);
                            return "This is Thread-2-data...";
                        }
                    });
                    System.out.println("Thread-2:" + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
