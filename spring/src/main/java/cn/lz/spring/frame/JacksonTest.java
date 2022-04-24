package cn.lz.spring.frame;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BeanPropertyBindingResult;

public class JacksonTest {
    public static void main(String[] args) {
        try {
            BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult("", "");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            String s = objectMapper.writeValueAsString(bindingResult);
//            String s = objectMapper.writeValueAsString(new Pat());
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
