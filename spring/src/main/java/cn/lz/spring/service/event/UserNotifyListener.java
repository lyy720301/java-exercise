package cn.lz.spring.service.event;

import cn.lz.spring.po.UserPo;
import cn.lz.spring.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserNotifyListener implements ApplicationListener<UserAddEvent> {

    @Override
    public void onApplicationEvent(UserAddEvent event) {
        try {
            UserPo userPo = JacksonUtil.readValue((String) event.getSource(), UserPo.class);
            log.info("user notify listener {}", userPo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
