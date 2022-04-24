package cn.lz.spring.service.cache;

import cn.lz.spring.po.UserPo;
import cn.lz.spring.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCacheService {

    @Cacheable(value = "user_cache", key = "#id", unless = "#result == null")
    public UserPo getUser(Integer id) {
        log.info("not query user in cache");
        return AuthUtil.getCurrentUser();
    }


}
