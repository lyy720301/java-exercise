package cn.lz.spring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JacksonUtil {

    private static final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    private JacksonUtil() {
    }

    public static <T> T readValue(String json, Class<T> tClass) throws JsonProcessingException {
        return objectMapper.readValue(json, tClass);
    }

    public static String writeValue(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
