package com.javasm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

public class DateUtilJavasm {

    //私有化构造方法，当前类为纯工具类
    private DateUtilJavasm() {
    }

    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_TIME = "HH:mm:ss";
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param time1
     * @param time2
     * @return 计算两个毫秒数之间相差天数
     */
    public static int differenceDay(Long time1, Long time2) {
        if (time1 == null || time2 == null) {
            return 0;
        }
        return differenceDay(new Date(time1), new Date(time2));
    }

    /**
     * 计算两个Date时间相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differenceDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        LocalDateTime localDateTime1 = date1.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        LocalDateTime localDateTime2 = date2.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return differenceDay(localDateTime1, localDateTime2);
    }

    /**
     * 计算两个LocalDateTime时间相差天数
     *
     * @param localDateTime1
     * @param localDateTime2
     * @return
     */
    public static int differenceDay(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        if (localDateTime1 == null || localDateTime2 == null) {
            return 0;
        }
        return (int) Math.abs(Duration.between(localDateTime1, localDateTime2).toDays());
    }

    /**
     * 任意时间转换为任意格式
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String timeToString(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            throw new NullPointerException("传入时间为空");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String format = dateTime.format(dateTimeFormatter);
        return format;
    }

    /**
     * 获取现在日期 yyyy-MM-dd
     *
     * @return
     */
    public static String nowDate() {
        return nowDateTime(PATTERN_DATE);
    }

    /**
     * 获取现在时间 HH:mm:ss
     *
     * @return
     */
    public static String nowTime() {
        return nowDateTime(PATTERN_TIME);
    }

    /**
     * 获取现在日期和时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String nowDateTime() {
        return nowDateTime(PATTERN_DATE_TIME);
    }

    /**
     * 根据格式化字符串获取现在时间
     *
     * @param pattern
     * @return
     */
    public static String nowDateTime(String pattern) {
        if (pattern == null) {
            throw new NullPointerException("格式化字符串不能为空");
        }
        return timeToString(LocalDateTime.now(), pattern);
    }

    /**
     * 传入yyyy-MM-dd格式的字符串，返回时间Date
     *
     * @param time
     * @return
     */
    public static Date stringToDate(String time) {
        return stringToDate(time, PATTERN_DATE);
    }

    /**
     * 传入yyyy-MM-dd HH:mm:ss格式的字符串，返回时间Date
     *
     * @param time
     * @return
     */
    public static Date stringAllToDate(String time) {
        return stringToDate(time, PATTERN_DATE_TIME);
    }

    public static Date stringToDate(String time, String pattern) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 传入yyyy-MM-dd HH:mm:ss格式的字符串，返回时间LocalDateTime
     * @param time
     * @return
     */
    public static LocalDateTime stringAllToLocalDateTime(String time){
        return stringToLocalDateTime(time,PATTERN_DATE_TIME);
    }
    /**
     * 传入yyyy-MM-dd格式的字符串，返回时间LocalDateTime
     * @param time
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String time){
        return stringToLocalDateTime(time,PATTERN_DATE);
    }

    /**
     * 根据不同的格式 转换字符串为LocalDateTime
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String time, String pattern) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time,dateTimeFormatter);
    }

    /**
     * 传入时间Date，返回yyyy-MM-dd格式的字符串
     * @return
     */
    public static String dateToString(Date date){
        return dateToString(date,PATTERN_DATE);
    }

    /**
     * 传入时间Date，返回yyyy-MM-dd HH:mm:ss格式的字符串
     * @param date
     * @return
     */
    public static String dateToStringAll(Date date){
        return dateToString(date,PATTERN_DATE_TIME);
    }

    /**
     * 任意时间转换为任意格式的字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date,String pattern){
        if (date == null){
            return null;
        }
        if (pattern == null || pattern.isEmpty()){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 传入时间LocalDateTime，返回yyyy-MM-dd格式的字符串
     * @param localDateTime
     * @return
     */
    public static String timeToString(LocalDateTime localDateTime){
        return timeToString(localDateTime,PATTERN_DATE);
    }

    /**
     * 传入时间LocalDateTime，返回yyyy-MM-dd HH:mm:ss格式的字符串
     * @param localDateTime
     * @return
     */
    public static String timeToStringAll(LocalDateTime localDateTime){
        return timeToString(localDateTime,PATTERN_DATE_TIME);
    }

    /**
     * 传入毫秒数，返回yyyy-MM-dd格式字符串
     * @param millisSeconds
     * @return
     */
    public static String millisToString(Long millisSeconds){
        return dateToString(new Date(millisSeconds));
    }
    /**
     * 传入毫秒数，返回yyyy-MM-dd HH:mm:ss格式字符串
     * @param millisSeconds
     * @return
     */
    public static String millisToStringAll(Long millisSeconds){
        return dateToStringAll(new Date(millisSeconds));
    }

    /**
     * 判断传入的时间是否为润年
     * @param localDateTime
     * @return
     */
    public static boolean isLeapYear(LocalDateTime localDateTime){
        return localDateTime.toLocalDate().isLeapYear();
    }

    /**
     * 判断传入的时间是否为润年
     * @param date
     * @return
     */
    public static boolean isLeapYear(Date date){
        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return isLeapYear(localDateTime);
    }

    /**
     * 判断传入的时间是否为润年
     * @param millisSeconds
     * @return
     */
    public static boolean isLeapYear(long millisSeconds){
        return isLeapYear(new Date(millisSeconds));
    }

    /**
     * 判断两个时间是否在同一周
     * @param localDateTime1
     * @param localDateTime2
     * @return
     */
    public static boolean isSameWeek(LocalDateTime localDateTime1,LocalDateTime localDateTime2){
        /*if (localDateTime1.get(WeekFields.ISO.weekOfYear()) == localDateTime2.get(WeekFields.ISO.weekOfYear())){
            return true;
        }*/
        if (localDateTime1.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == localDateTime2.get(ChronoField.ALIGNED_WEEK_OF_YEAR)){
            return true;
        }
        return false;
    }

    /**
     * 判断两个时间是否在同一周
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeek(Date date1,Date date2){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        if (calendar1.get(Calendar.WEEK_OF_YEAR) == calendar2.get(Calendar.WEEK_OF_YEAR)){
            return true;
        }
        return false;
    }

    /**
     * 传入时间返回周几
     * @param localDateTime
     * @return
     */
    public static String getWeek(LocalDateTime localDateTime){
        return getWeek(localDateTime.toLocalDate());
    }
    public static String getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) -1;
        if (week == 0){
            week =7;
        }
        return switchWeek(week);
    }
    public static String getWeek(LocalDate time){
        return switchWeek(time.get(ChronoField.DAY_OF_WEEK));
    }
    public static String getWeek(Long time){
        return getWeek(new Date(time));
    }

    private static String switchWeek(int week){
        switch (week){
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
        }
        return "周日";
    }



}
