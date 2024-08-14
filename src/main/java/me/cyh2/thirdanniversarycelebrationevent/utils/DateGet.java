package me.cyh2.thirdanniversarycelebrationevent.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.EndDate;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.StartDate;

public class DateGet {
    public static long getTimeStamp (String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(date, formatter);
        Instant instant = date1.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }
    public static float getDay (long milliseconds) {
        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;
        return (float) milliseconds / oneDayInMilliseconds;
    }
    public static boolean isStart () {
        return (StartDate - Instant.now().toEpochMilli()) <= 0 && (EndDate - Instant.now().toEpochMilli()) >= 0;
    }
}
