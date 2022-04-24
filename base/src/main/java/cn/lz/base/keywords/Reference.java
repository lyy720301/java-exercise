package cn.lz.base.keywords;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 值引用和对象引用
 */
@Slf4j
public class Reference {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("before", "before");
        reference(map);
        // actual results {"before", "before"}
        log.info(map.toString());
    }

    static void reference(Map<String, String> map) {
        map = new HashMap<>();
        map.put("after", "after");
    }
}
