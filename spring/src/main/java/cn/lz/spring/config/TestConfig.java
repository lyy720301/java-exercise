package cn.lz.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource("classpath:test-config.properties")
public class TestConfig implements CommandLineRunner {

    @Value("${test.name}")
    private String name;

    @Override
    public void run(String... args) throws Exception {
        log.info("name {}", name);
    }
}
