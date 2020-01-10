package ua.com.parkhub;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Runner {
    public static void main(String[] args) {
        long longValue = 1578597201000L;
        LocalDateTime date =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
        System.out.println(date);

        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long plusHour = LocalDateTime.now().plusHours(1).toEpochSecond(ZoneOffset.UTC);
        System.out.println(now);
        System.out.println(plusHour);
    }
}
