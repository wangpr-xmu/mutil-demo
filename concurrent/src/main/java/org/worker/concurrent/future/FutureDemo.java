package org.worker.concurrent.future;

import java.util.concurrent.*;

/**
 * @author peiru wang
 * @date 2021/6/24
 */
public class FutureDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                3,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10)
        );

        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
                return "Hello world";
            }
        };
        FutureTask<String> futureTask = new FutureTask<String>(call);


//        executor.submit(call);

        new Thread(futureTask).start();
        Future<String> submit = executor.submit(call);

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


    }
}
