package cn.lz.spring.config.aop;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdviceConfig {

    /**
     * 注册日志AOP切面
     */
    @Bean
    public AspectJExpressionPointcutAdvisor logPointcutAdvisor(LogAdvice logAdvice) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(public * cn.lz.spring.controller.AdviceAopController.*(..))");
        advisor.setAdvice(logAdvice);
        advisor.setOrder(0);
        return advisor;
    }

}
