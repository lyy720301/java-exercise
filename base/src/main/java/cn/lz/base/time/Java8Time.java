package cn.lz.base.time;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Java8Time {
    public static void main(String[] args) {
        Date now = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(now.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIDNIGHT);
        Date date = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
}
