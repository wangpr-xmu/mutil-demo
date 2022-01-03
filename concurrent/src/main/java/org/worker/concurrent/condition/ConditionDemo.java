package org.worker.concurrent.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    public static void main(String[] args) {

        ConditionTest test = new ConditionTest(new ReentrantLock(), "hello");

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.print();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.append(" world");
            }
        }).start();

    }
}

class ConditionTest {
    private Lock lock;
    private Condition condition;
    private String str;

    public ConditionTest(Lock lock, String str) {
        this.lock = lock;
        this.condition = lock.newCondition();
        this.str = str;
    }

    public void append(String s) {
        try {
            lock.lock();
            System.out.println("append");
            str = str + s;
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void print() {
        try {
            lock.lock();
            System.out.println("print");
            condition.await();
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
