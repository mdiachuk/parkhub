package ua.com.parkhub;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class Runner {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plusHour = LocalDateTime.now().plusHours(1);
        System.out.println(ChronoUnit.HOURS.between(now, plusHour));
    }
}
