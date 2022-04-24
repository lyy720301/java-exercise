package cn.lz.base.enums;

import cn.lz.base.entity.Sex;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class LearnEnumMap {

    public static void main(String[] args) {
        Map<Sex, Integer> map = new EnumMap<>(Sex.class);
        for (Sex sex : Sex.values()) {
            map.putIfAbsent(sex, 1);
        }

        Map<Sex, Integer> generalMap = new HashMap<>();
        for (Sex sex : Sex.values()) {
            generalMap.putIfAbsent(sex, 1);
        }
        Integer integer = generalMap.get(Sex.MAN);
        System.out.println(integer);
    }
}
