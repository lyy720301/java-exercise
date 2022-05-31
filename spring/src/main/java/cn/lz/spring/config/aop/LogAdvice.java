package cn.lz.spring.config.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class LogAdvice implements MethodInterceptor {

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // 获取被拦截方法
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String uri = request.getRequestURI();
        String mark = String.format("调用方法: %s", uri);
        /**
         * 使用logback的MDC（Mapped Diagnostic Context，映射调试上下文）机制
         * 记录业务日志方法调用链唯一ID
         */
        MDC.put("uuid", UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

        // 获取方法执行开始时间点
        long startTimestamp = Instant.now().toEpochMilli();

        Object proceed = null;
        try {
            // 前置通知
            Object[] args = invocation.getArguments();
            Object[] targetArgs = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof ServletRequest
                        || args[i] instanceof ServletResponse
                        || args[i] instanceof MultipartFile
                        || args[i] instanceof BindingResult) {
                    // 如果使用httpServletRequest对象进行传参则不能打印日志，因为httpServletRequest对象不能序列化
                } else {
                    targetArgs[i] = args[i];
                }
            }
            log.info("{} 入参：{}", mark, objectMapper.writeValueAsString(targetArgs));

            // 调用代理方法
            proceed = invocation.proceed();
        } catch (Throwable throwable) {
            log.error("{} 未知异常：{}", mark, throwable);
            throw throwable;
        } finally {
            long elapsed = Instant.now().toEpochMilli() - startTimestamp;
            // 停止上一个计时器
            log.info("{} 耗时:{}", mark, elapsed);
            log.info("{} 出参：{}", mark, objectMapper.writeValueAsString(proceed));
        }

        return proceed;
    }
}