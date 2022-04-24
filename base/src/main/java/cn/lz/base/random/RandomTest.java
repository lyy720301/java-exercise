package cn.lz.base.random;

public class RandomTest {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        long time1 = System.currentTimeMillis() % 10000000;
        System.out.println(time);
        System.out.println(time1);
    }
}
