package ua.com.parkhub.util.formatter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static String convertDateTimeToString(LocalDateTime localDate) {
        return localDate.format(formatter);
    }

    public static LocalDateTime covertMillisToLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    public static LocalDateTime convertTimeStampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static void main(String[] args) {
        System.out.println(convertTimeStampToLocalDateTime(Timestamp.valueOf("2020-01-12 12:22:05.000000")));
    }
}
