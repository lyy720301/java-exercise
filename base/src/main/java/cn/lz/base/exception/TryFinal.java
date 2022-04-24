package cn.lz.base.exception;

public class TryFinal {

    public static void main(String[] args) {
        /*
        OUT:
            before
            final
            1
         */
        System.out.println(testFinal());
    }

    static int testFinal() {
        try {
            return before();
        } finally {
            System.out.println("final");
        }
    }

    static int before() {
        System.out.println("before");
        return 1;
    }
}
