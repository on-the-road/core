package com.example.core.task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 1.类共享变量遭遇并发
 */
public class ThreadSafe {
    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static void main(String[] args) {
        final ThreadSafe ts = new ThreadSafe();
        Executor executor = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 1000; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    ts.count++;
                }
            });
        }

        //主线程sleep一段时间
        try{
            Thread.sleep(9000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        System.out.println("final count value:" + ts.count);

    }
}
