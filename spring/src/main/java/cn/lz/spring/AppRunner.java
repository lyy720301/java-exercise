package cn.lz.spring;

import cn.lz.spring.service.cache.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 应用启动完成后执行
 */
@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public void run(String... args) throws Exception {
        log.info("commandLineRunner");
        // 测试缓存
        log.info("user {}", userCacheService.getUser(1));
        log.info("user {}", userCacheService.getUser(1));
        log.info("user {}", userCacheService.getUser(1));
    }
}
