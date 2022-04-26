package cn.lz.base.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiFunction;

public class MapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("h3", "h3");
        linkedHashMap.put("h2", "h2");
        linkedHashMap.put("h1", "h1");
        linkedHashMap.put("h0", "h0");
        linkedHashMap.forEach((k, v) -> System.out.printf("k: %s; v: %s\n", k, v));
        System.out.println("==========");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("h3", "h3");
        hashMap.put("h2", "h2");
        hashMap.put("h1", "h1");
        hashMap.put("h0", "h0");
        hashMap.forEach((k, v) -> System.out.printf("k: %s; v: %s\n", k, v));

        BiFunction<String, String, String> remappingFunction = (oldValue, newValue) -> oldValue + newValue;
        hashMap.merge("h5", "h6", remappingFunction);
        hashMap.merge("h0", "h0", remappingFunction);
        System.out.println(hashMap);
    }
}
