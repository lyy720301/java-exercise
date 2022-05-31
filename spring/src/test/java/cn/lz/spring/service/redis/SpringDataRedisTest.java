package cn.lz.spring.service.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    private SpringDataRedis springDataRedis;

    @Test
    public void testInsertList() {
        springDataRedis.insertList();
    }

    @Test
    public void testLogback() {

    }
}
