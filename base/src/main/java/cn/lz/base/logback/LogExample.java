package cn.lz.base.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import cn.lz.base.logback.son.SonLogExample;
import org.slf4j.LoggerFactory;

public class LogExample {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(LogExample.class);




    public static void main(String[] args) {
        logger.info("print: {}", "log");
        logger.debug("debug");

        logger.setLevel(Level.DEBUG);
        Logger sonLogger = (Logger) LoggerFactory.getLogger(SonLogExample.class);


        sonLogger.debug("son log debug");
    }
}
