package cn.lz.base.thread.local;

public class ThreadLocalTest {

    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // inheritableThreadLocal
        inheritableThreadLocal.set("我是主线程");
        System.out.println(inheritableThreadLocal.get() + Thread.currentThread().getName());
        new Thread(() -> System.out.println(inheritableThreadLocal.get() + Thread.currentThread().getName())).start();

        // threadLocal
        threadLocal.set("thread local main thread");


    }
}
