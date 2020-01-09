package ua.com.parkhub.util.formatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    private static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String convertDateTimeToString(LocalDateTime localDate) {
        return localDate.format(formatter1);
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter2);
    }

    public static LocalDateTime covertMillisToLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    public static void main(String[] args) {
        String dateTime = "2019-12-23 14:58";
        System.out.println(convertStringToLocalDateTime(dateTime));
    }


}
