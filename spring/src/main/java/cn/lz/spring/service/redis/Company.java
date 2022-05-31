package cn.lz.spring.service.redis;

import org.springframework.stereotype.Component;

@Component
public class Company {

    private final String name = "抖音";

    public Company() {
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
