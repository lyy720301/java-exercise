package cn.lz.base.thread.local;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每个线程有一个Id，多个线程之间的Id不得重复
 */
@Slf4j
public class ThreadId {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(atomicInteger::getAndIncrement);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<FutureTask<Integer>> tasks = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> {
                log.info("{}", threadLocal.get());
                return 0;
            });
            tasks.add(futureTask);
            threadPool.submit(futureTask);
        }
        tasks.forEach(futureTask -> {
            try {
                futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        threadLocal.remove();
    }
}
