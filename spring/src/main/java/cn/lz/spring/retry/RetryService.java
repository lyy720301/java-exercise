package cn.lz.spring.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * 注解式
 */
@Slf4j
@Service
public class RetryService {

    @Retryable(RemoteAccessException.class)
    public int service() {
        log.error("throw a RemoteAccessException");
        this.throwException();
        return 0;
    }

    @Recover
    public int recover(RemoteAccessException e) {
        // ... panic
        log.error("retry fail, recover 1", e);
        return 1;
    }

    public void throwException() {
        throw new RemoteAccessException("mock fail");
    }
}
