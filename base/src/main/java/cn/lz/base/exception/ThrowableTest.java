package cn.lz.base.exception;

public class ThrowableTest {
    public static void main(String[] args) {
        throw new RuntimeException(new IllegalArgumentException("illegal"));
    }
}
