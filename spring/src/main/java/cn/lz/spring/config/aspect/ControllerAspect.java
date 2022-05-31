package cn.lz.spring.config.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@Aspect
public class ControllerAspect {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before("cn.lz.spring.config.aspect.CommonJoinPointConfig.allControllerExecution()" +
            "&& cn.lz.spring.config.aspect.CommonJoinPointConfig.anyPublicOperation()")
    public void beforeController() {
    }

    @Around("cn.lz.spring.config.aspect.CommonJoinPointConfig.allControllerExecution()" +
            "&& cn.lz.spring.config.aspect.CommonJoinPointConfig.anyPublicOperation()")
    public Object printControllerLog(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String uri = request.getRequestURI();
        List<Object> argList = Lists.newArrayList();
        Collections.addAll(argList, pjp.getArgs());
        // 略过不能json序列化的对象
        argList.removeIf(arg -> arg instanceof ServletResponse || arg instanceof ServletRequest || arg instanceof MultipartFile || arg instanceof BindingResult);
        log.info("request uri: {} , in args: {}", uri, objectMapper.writeValueAsString(argList));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();
        log.info("request uri: {} , out args: {}", uri, result);
        log.info("request uri: {} , proceed time {} millis", uri, stopWatch.getTotalTimeMillis());
        return result;
    }
}
