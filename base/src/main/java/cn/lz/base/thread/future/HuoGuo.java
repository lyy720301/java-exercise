package cn.lz.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * future 入门 https://mp.weixin.qq.com/s/nsYzSHdZ4HlcB8jcw2CHUw
 */
@Slf4j
public class HuoGuo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("准备搞火锅");
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("准备烧开水");
            Thread.sleep(5000);
            log.info("开水烧好了");
            return "开水";
        });
        async(futureTask);
        // 这里是同步执行
//        sync(futureTask);

    }

    private static void async(FutureTask<String> futureTask) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(futureTask);
        thread.start();
        log.info("开始准备食材");
        Thread.sleep(2000);
        log.info("食材准备好了");
        String taskResult = futureTask.get();
        log.info("拿到{}了", taskResult);
        log.info("开始吃火锅！");
    }

    private static void sync(FutureTask<String> futureTask) throws ExecutionException, InterruptedException {
        // 如果调用了sync()，则这里则不会执行
        futureTask.run();
        String result = futureTask.get();
        log.info("future get {}", result);
    }
}
