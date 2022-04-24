package cn.lz.spring.service.event;

import cn.lz.spring.po.UserPo;
import cn.lz.spring.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <i>spring4.2之后 ApplicationEventPublisher自动被注入到容器中，不再需要
 * 实现ApplicationEventPublisherAware接口</i>
 * <p/>
 * 发布事件
 */
@Slf4j
@Service
public class UserAddPublisherService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void insert(UserPo userPo) {
        try {
            UserAddEvent userAddEvent = new UserAddEvent(JacksonUtil.writeValue(userPo));
            log.info("publish add user event {}", userPo);
            applicationEventPublisher.publishEvent(userAddEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.info("publish add user event {} error {}", userPo, e);
        }
    }

}
