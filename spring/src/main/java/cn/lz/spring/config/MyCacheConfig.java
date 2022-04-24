package cn.lz.spring.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class MyCacheConfig extends CachingConfigurerSupport {

    @Autowired
    private Caffeine caffeine;

    @Bean
    public Caffeine caffeineConfig() {
        return
                Caffeine.newBuilder()
                        .maximumSize(10000).
                        expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Override
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

}