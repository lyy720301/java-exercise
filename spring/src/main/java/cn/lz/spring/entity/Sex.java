package cn.lz.spring.entity;

public enum Sex {

    MAN(0, "男"),
    WOMAN(1, "女");

    final int code;
    final String name;

    Sex(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
