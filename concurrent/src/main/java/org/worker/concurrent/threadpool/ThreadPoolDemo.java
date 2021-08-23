package org.worker.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author peiru wang
 * @date 2021/6/21
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,//核心线程数
                10,//最大线程数
                60,//存活时间
                TimeUnit.SECONDS,//时间单位
                new ArrayBlockingQueue<Runnable>(10),//任务队列
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                }, //
                new ThreadPoolExecutor.AbortPolicy()
        );

        executor.execute(new Runnable() {
            public void run() {
                System.out.println("hello world");
            }
        });

        try {
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
