package cn.lz.base.exception;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.List;

@Slf4j
public class HeapStackTest {

    private static final Logger logger
            = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(HeapStackTest.class);
    public static void main(String[] args) {
        try {
            List list = getList();
            list.add("1");
        } catch (Exception e) {
            logger.info("发生了异常",e);
            log.info("发生了异常", e);
        }
    }

    private static List getList() {
        return null;
    }
}
