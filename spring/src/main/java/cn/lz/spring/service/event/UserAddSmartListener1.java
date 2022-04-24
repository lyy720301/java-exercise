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
public class UserAddSmartListener1 implements SmartApplicationListener {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        // 返回： eventType代表的类是不是UserAddEvent或者它的子类
        return UserAddEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof UserAddEvent) {
            UserAddEvent userAddEvent = (UserAddEvent) event;
            try {
                UserPo userPo = JacksonUtil.readValue((String) userAddEvent.getSource(), UserPo.class);
                log.info("order 1 listener {}", userPo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
