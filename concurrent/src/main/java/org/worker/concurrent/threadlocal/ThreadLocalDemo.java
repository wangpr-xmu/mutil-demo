package org.worker.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author peiru wang
 * @date 2021/9/12
 */
public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName());
                threadLocal.set(Thread.currentThread().getName() + "12");

                System.out.println("thread1");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t1" + threadLocal.get());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName());

                System.out.println("thread2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t2" + threadLocal.get());
            }
        });

        t2.start();
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
