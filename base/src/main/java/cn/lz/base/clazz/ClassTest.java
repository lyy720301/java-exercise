package cn.lz.base.clazz;

public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String canonicalName = ClassTest.class.getCanonicalName();
        System.out.println(canonicalName);
        // 加载内部类或者静态内部类用$
        Class.forName("cn.lz.base.clazz.ClassTest$My");
    }

    static class My {
        static {
            System.out.println("hello, class");
        }
    }
}
