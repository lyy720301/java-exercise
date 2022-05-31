package cn.lz.spring.config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonJoinPointConfig {

    @Pointcut("within(cn.lz.spring.controller..*)")
    public void allControllerExecution() {
    }

    @Pointcut("execution(public * *(..))")
    public void anyPublicOperation() {
    }
}
