package cn.lz.base.random;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                int num = ThreadLocalRandom.current().nextInt(100);
                System.out.println(Thread.currentThread().getId() + " : " + num);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
