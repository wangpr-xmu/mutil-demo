package org.worker.concurrent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author peiru wang
 * @date 2021/6/15
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for(int i = 0; i < 20; i++) {
            new Thread(new Slide(semaphore, i)).start();
        }
    }
}

class Slide implements Runnable {
    private Semaphore semaphore;
    private int slideNum;

    public Slide(Semaphore semaphore, int slideNum) {
        this.semaphore = semaphore;
        this.slideNum = slideNum;
    }

    public void run() {
        try {
            semaphore.acquire();
            System.out.println("编号：" + slideNum + "占用一个滑道");
            TimeUnit.SECONDS.sleep(2);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
