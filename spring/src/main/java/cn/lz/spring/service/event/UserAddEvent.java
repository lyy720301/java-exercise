package cn.lz.spring.service.event;

import org.springframework.context.ApplicationEvent;

public class UserAddEvent extends ApplicationEvent {

    public UserAddEvent(Object source) {
        super(source);
    }
}
