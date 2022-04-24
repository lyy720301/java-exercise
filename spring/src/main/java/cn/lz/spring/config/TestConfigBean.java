package cn.lz.spring.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 如果该class没有@Component标注，则需要在Main class 上声明@EnableConfigurationProperties
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("test")
public class TestConfigBean {

    private String name;

    private String parent;
}
