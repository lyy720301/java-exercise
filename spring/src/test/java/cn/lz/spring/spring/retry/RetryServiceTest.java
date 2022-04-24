package cn.lz.spring.spring.retry;

import cn.lz.spring.retry.RetryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetryServiceTest {

    @SpyBean
    private RetryService retryService;

    @Test
    public void testRetry() {
        Assert.assertEquals(1, retryService.service());
    }
}
