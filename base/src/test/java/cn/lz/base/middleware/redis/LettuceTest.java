package cn.lz.base.middleware.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisSortedSetCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LettuceTest {

    @Test
    public void test1() {
        RedisClient client = RedisClient.create(RedisURI.builder().withHost("192.168.13.130").withPort(6380).build());
        try (StatefulRedisConnection<String, String> connection = client.connect();) {
            RedisSortedSetCommands<String, String> sync = connection.sync();
            Double zScore = sync.zscore("zset1", "dog");
            log.info("zScore: {}", zScore);
        } finally {
            client.shutdown();
        }
    }

}
