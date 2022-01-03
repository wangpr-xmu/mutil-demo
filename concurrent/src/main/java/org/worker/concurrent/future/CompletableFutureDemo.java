package org.worker.concurrent.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author peiru wang
 * @date 2021/9/5
 */
public class CompletableFutureDemo {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3,
            10,
            5000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            },
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.out.println("丢弃任务： " + r);
                }
            }
    );

    public static void main(String[] args) {

//        Executors.newCachedThreadPool();
//        Executors.newFixedThreadPool(3);
//        Executors.newSingleThreadExecutor();
//        Executors.newScheduledThreadPool(3);
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1000L;
            }
        }, executor);

        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1000L;
            }
        }, executor);

        CompletableFuture<Long> future3 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1000L;
            }
        }, executor);

        try {
            System.out.println(future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<CompletableFuture<Long>> completableFutures = new ArrayList(3);
        completableFutures.add(future1);
        completableFutures.add(future2);
        completableFutures.add(future3);

        CompletableFuture.allOf(future1, future2, future3).thenAccept(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) {
                try {
                    System.out.println(future1.get() + future2.get() + future3.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });


//        CompletableFuture.supplyAsync(new Supplier<String>() {
//            @Override
//            public String get() {
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return "hello";
//            }
//        }, executor).thenApply(result -> {
//            return result + " world";
//        }).thenAccept(finalResult ->  {
//            System.out.println(finalResult);
//        });
    }
}
