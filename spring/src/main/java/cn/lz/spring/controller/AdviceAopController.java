package cn.lz.spring.controller;

import cn.lz.spring.po.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdviceAopController {

    /**
     * 测试第二种切面打Controller日志
     */
    @PostMapping("advice")
    public String advice(@RequestBody UserPo userPo) {
        log.info("advice controller.advice()");
        return userPo.toString();
    }
}
