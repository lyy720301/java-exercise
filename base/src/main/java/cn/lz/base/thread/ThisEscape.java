package cn.lz.base.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * this逸出，期待name为foo，输出的却为null
 */
@Slf4j
public class ThisEscape {

    Object ref;

    public ThisEscape(Object ref) {
        this.ref = ref;
        log.info("{}", this.ref.toString());
    }

    public static void main(String[] args) {
        new Bar();
    }

    static class Foo {

        private final String name;

        public Foo() {
            setup();
            name = "foo";
        }

        /**
         * 构造方法中调用了该方法，且该方法可以由子类覆盖，无法预测方法覆盖后的行为，有this逸出的风险。
         */
        public void setup() {

        }

        @Override
        public String toString() {
            return "Foo{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Bar extends Foo {

        @Override
        public void setup() {
            new ThisEscape(this);
        }
    }
}
