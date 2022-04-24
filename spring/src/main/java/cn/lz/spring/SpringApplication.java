package cn.lz.spring;

import cn.lz.spring.config.TestConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

@Slf4j
@EnableRetry
@ServletComponentScan("cn.lz.spring.filter")
@SpringBootApplication
@MapperScan("cn.lz.spring.dao")
public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        TestConfigBean bean = context.getBean(TestConfigBean.class);
        log.info("config properties bean test {}", bean);
    }

}
