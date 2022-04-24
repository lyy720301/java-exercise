package cn.lz.spring.normal;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

@Slf4j
public class ReferenceDemo {
    public static void main(String[] args) {
        /*
        软引用 只有快oom了才会回收
         */
        SoftReference<String> softReference = new SoftReference<String>("1");
        String s = softReference.get();
        System.gc();
        log.info("soft {}", softReference.get() == null ? "null" : "not null");

        /*
        弱引用 每次gc都会回收
         */
        String str = "1";
        WeakReference<String> weakReference = new WeakReference<String>(str);
        log.info(weakReference.get());
        System.gc();
        /*
         为什么这里没有回收？
         因为发出gc命令之后JVM不一定一定会执行
         */
        log.info("weak {}", weakReference.get() == null ? "null" : "not null");
        log.info("");
    }
}
