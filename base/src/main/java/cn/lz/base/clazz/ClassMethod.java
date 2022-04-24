package cn.lz.base.clazz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassMethod {
    public static void main(String[] args) {
        Class<ClassMethod> classMethodClass = ClassMethod.class;
        log.info("typeName {}", classMethodClass.getTypeName());
        log.info("name {}", classMethodClass.getName());
        log.info("CanonicalName {}", classMethodClass.getCanonicalName());

        // array
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Class<? extends int[]> aClass = arr.getClass();
        log.info("name {}", aClass.getName());
        log.info("typeName {}", aClass.getTypeName());
        log.info("CanonicalName {}", aClass.getCanonicalName());

        int[][] arr_arr = new int[][]{{2}};
        Class<? extends int[][]> aClass1 = arr_arr.getClass();
        log.info("name {}", aClass1.getName());
        log.info("typeName {}", aClass1.getTypeName());

    }
}
