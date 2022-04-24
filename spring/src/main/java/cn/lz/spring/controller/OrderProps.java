package cn.lz.spring.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "entity.order")
@Component
@Data
public class OrderProps {

    private String description;
}
