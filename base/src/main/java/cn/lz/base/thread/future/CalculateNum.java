package cn.lz.base.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.UnaryOperator;

public class CalculateNum {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        CompletableFuture<Object> funA = new CompletableFuture<>();
        threadPool.submit(() -> funA.complete(sum(1, 2)));
        int delRes = del(3, 1);

        System.out.printf("sum %s; del %s \n", funA.get(), delRes);
        threadPool.shutdown();
    }

    private static int sum(int a, int b) {
        return a + b;
    }

    private static int del(int a, int b) {
        return a - b;
    }

    public void fun1() {
        fun((String arg) -> arg + "2");
    }

    public void fun(UnaryOperator<String> fun) {
        fun.apply("hello");
    }
}
