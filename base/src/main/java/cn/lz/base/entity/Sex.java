package cn.lz.base.entity;

public enum Sex {

    MAN(0, "男"),
    WOMAN(1, "女");

    int code;
    String name;

    Sex(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
