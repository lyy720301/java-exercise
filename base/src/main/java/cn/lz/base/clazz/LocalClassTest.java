package cn.lz.base.clazz;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class LocalClassTest {

    public static void main(String[] args) {
        LocalClassTest localClassTest = new LocalClassTest();
        log.info("{}", localClassTest.fun("adf123"));
        Class<Function> functionClass = Function.class;
        System.out.println(functionClass.toGenericString());
        System.out.println(LocalClassTest.class.toGenericString());
    }

    public boolean fun(String number) {

        class Valid {

            public Valid() {
            }

            public boolean valid() {
                char[] chars = number.toCharArray();
                boolean flag = true;
                for (int i = 0; i < chars.length && flag; i++) {
                    flag = Character.isDigit(chars[i]);
                }
                return flag;
            }
        }
        Class<?> enclosingClass = Valid.class.getEnclosingClass();
        // LocalClassTest
        System.out.println(enclosingClass.getName());
        return new Valid().valid();
    }
}
