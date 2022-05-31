package cn.lz.base.thread;

import java.util.concurrent.CountDownLatch;

public class CountDown {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("new thread");
            latch.countDown();
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我不会执行");
    }
}
