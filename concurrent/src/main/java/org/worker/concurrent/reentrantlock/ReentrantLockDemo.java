package org.worker.concurrent.reentrantlock;

/**
 * @author peiru wang
 * @date 2021/6/15
 */
public class ReentrantLockDemo {

    private static int count = 0;

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    count++;
                }
            }.start();
        }

        System.out.println(count);
    }
}
