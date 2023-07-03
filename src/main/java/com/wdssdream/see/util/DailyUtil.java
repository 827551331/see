package com.wdssdream.see.util;

import com.wdssdream.see.entity.Daily;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DailyUtil {


    public static int getCurrentWeekOfMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        if (firstDayOfMonth.getDayOfWeek().getValue() == 6) {
            firstDayOfMonth = firstDayOfMonth.plusDays(2);
        }
        if (firstDayOfMonth.getDayOfWeek().getValue() == 7) {
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }
        return (currentDate.getDayOfMonth() - firstDayOfMonth.getDayOfMonth()) / 7 + 1;
    }

    public static int getCurrentWeekOfYear() {
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return currentDate.get(weekFields.weekOfYear());
    }

    public static String getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return currentDate.format(formatter);
    }

    public static String getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        return currentDate.format(yearFormatter);
    }

    public static Daily wrap(Daily daily) {
        daily.setWeekOfMonth(DailyUtil.getCurrentWeekOfMonth());
        daily.setWeekOfYear(DailyUtil.getCurrentWeekOfYear());
        daily.setDailyMonth(DailyUtil.getCurrentMonth());
        daily.setDailyYear(DailyUtil.getCurrentYear());
        daily.setUpdateTime(LocalDateTime.now());
        daily.setCreatePerson("admin");
        daily.setUpdatePerson("admin");
        return daily;
    }
}

