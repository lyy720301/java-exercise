package cn.lz.base.thread.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static cn.hutool.core.thread.ThreadUtil.sleep;

public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept(System.out::println)
                .thenApply((s) -> 7 / 0)
                .exceptionally(e ->
                        {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                            return 0;
                        }
                );
        System.out.println(exceptionally.join());

        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            System.out.println("wash teapot");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("boil water");
        });

        CompletableFuture<String> runAsync1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("wash teacup");
            System.out.println("put in tea");
            return "Longjing";
        });

        CompletableFuture<String> thenCombine = runAsync.thenCombine(runAsync1, (__, tf) -> {
            System.out.println("get tea:" + tf);
            return "complete Longjing tea";
        });

        System.out.println(thenCombine.join());

        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    int t = new Random().nextInt(10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    int t = new Random().nextInt(10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2, s -> s);

        System.out.println(f3.join());


    }
}
