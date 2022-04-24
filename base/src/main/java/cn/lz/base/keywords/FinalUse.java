package cn.lz.base.keywords;

public class FinalUse {

    public static void main(String[] args) {
        Num num = new Num();
        Num num1 = new Num();
        System.out.println(num.j);
        System.out.println(num1.j);
        System.out.println(num.i);
        // 和 num.i 相同
        System.out.println(num1.i);
    }

    static class Num {
        public static double i = Math.random();
        public final double j = Math.random();
    }
}
