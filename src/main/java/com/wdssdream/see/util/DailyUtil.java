package com.wdssdream.see.util;

import com.wdssdream.see.entity.Daily;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DailyUtil {

    public static LocalDate parseDate(String dateStr, String pattern) {
        if (!StringUtils.hasLength(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            // 处理日期解析异常
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(LocalDate date, String pattern) {
        if (!StringUtils.hasLength(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static int getCurrentWeekOfMonth() {
        return getWeekOfMonth(formatDate(LocalDate.now(), null));
    }

    public static int getWeekOfMonth(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return getWeekOfMonth(date);
    }

    public static int getWeekOfMonth(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfMonth());
    }

    public static int getCurrentWeekOfYear() {
        return getCurrentWeekOfYear(formatDate(LocalDate.now(), null));
    }

    public static int getCurrentWeekOfYear(String dateString) {
        LocalDate currentDate = LocalDate.parse(dateString);
        return getCurrentWeekOfYear(currentDate);
    }

    public static int getCurrentWeekOfYear(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfYear());
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
        daily.setCreateTime(LocalDateTime.now());
        daily.setUpdateTime(LocalDateTime.now());
        daily.setCreatePerson("admin");
        daily.setUpdatePerson("admin");
        return daily;
    }
}

