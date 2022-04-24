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
    }
}
