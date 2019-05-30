package com.example.core.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * @Author wangwei
 * @Date 2019/3/23 14:35
 * -描述-
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(now));
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        System.out.println(now.with(fieldISO, 2));
        System.out.println(now);
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));

        TemporalField fieldUS = WeekFields.of(Locale.CHINA).dayOfWeek();
        System.out.println(now.with(fieldUS, 2)); // 2015-02-08 (Sunday)
    }
}
