package cn.lz.base.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class AlternatelyPrint {

    public static void main(String[] args) {
        AtomicReference<Thread> reference = new AtomicReference<>();
        Thread ji = new Thread(() -> {
            int a = 0;
            while (true) {
                LockSupport.park();
                System.out.println(a += 2);
                LockSupport.unpark(reference.get());
            }

        });

        Thread ou = new Thread(() -> {
            int a = -1;
            while (true) {
                System.out.println(a += 2);
                LockSupport.unpark(ji);
                LockSupport.park();
            }
        });
        reference.set(ou);
        ji.start();
        ou.start();
    }
}
