package org.worker.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 模拟并发请求
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        for(int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("准备请求。。。");
                        latch.await();
                        System.out.println("并发请求。。。" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();;
        }

        latch.countDown();

        System.out.println("结束...");

    }
}
