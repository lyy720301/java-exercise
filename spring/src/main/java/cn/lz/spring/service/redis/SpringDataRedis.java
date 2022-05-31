package cn.lz.spring.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@Component
public class SpringDataRedis {

    @Resource(name = "company")
    private Human human;

    // inject the template as ListOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void insertList() {
        listOps.rightPushAll("l0526", "1", "2");
        List<String> l0526 = listOps.rightPop("l0526", 1);
        log.info("redis list data - {}", l0526);
        log.info("company - {}", human.getCompany());
    }

    @Resource
    public void companyInject(Company company1) throws NoSuchMethodException {
        log.info("{}", company1);
        Class<? extends SpringDataRedis> aClass = this.getClass();
        Method companyInject = aClass.getDeclaredMethod("companyInject", Company.class);
        companyInject.setAccessible(true);
        Resource annotation = companyInject.getAnnotation(Resource.class);
        log.info("annotation name : {}", annotation.name());
    }



}
