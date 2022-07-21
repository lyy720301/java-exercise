package cn.lz.base.thread;

import java.lang.management.ManagementFactory;

public class ThreadTest {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("pid " + name);
        new Thread(() -> {
            while (true) {
            }
        }, "thread1").start();

        new Thread(() -> System.out.println("hello " + Thread.currentThread().getName()), "thread2").start();

        new Thread(() -> System.out.println("hello " + Thread.currentThread().getName()), "thread2").start();

        new ThreadTest().interrupt();
    }

    private void interrupt() {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("not interrupt");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(1000);
            System.out.println("interrupt thread");
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
