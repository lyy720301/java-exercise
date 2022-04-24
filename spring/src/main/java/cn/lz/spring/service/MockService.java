package cn.lz.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MockService {

    public String doSomeThing() {
        try {
            TimeUnit.SECONDS.sleep(10);
            return "complete";
        } catch (InterruptedException e) {
            log.info("time unit error", e);
            e.printStackTrace();
        }
        return "fail";
    }
}
