package cn.lz.spring.service.event;

import cn.lz.spring.po.UserPo;
import cn.lz.spring.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAddSmartListener2 implements SmartApplicationListener {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return UserAddEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof UserAddEvent) {
            try {
                UserPo userPo = JacksonUtil.readValue((String) event.getSource(), UserPo.class);
                log.info("order 2 listener {}", userPo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}