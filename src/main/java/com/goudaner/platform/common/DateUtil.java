package com.goudaner.platform.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.util.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);


    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) return "";
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getCurrentDateToString() {
        return format(getCurrentDate(), YYYYMMDDHHMMSS);
    }


    /**
     * 格式化当前时间
     *
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return format(getCurrentDate(), pattern);
    }

    public static Integer formatNowInteger(String pattern) {
        return Integer.valueOf(formatNow(pattern));
    }

    /**
     * 格式化当前时间-yyyyMMddHHmmss
     *
     * @return
     */
    public static String formatNowTime() {
        return formatNow("yyyyMMddHHmmss");
    }

    /**
     * 格式化当前时间-yyyyMMddHHmmssSSSS
     *
     * @return
     */
    public static String formatNowTimeStamp() {
        return formatNow("yyyyMMddHHmmssSSSS");
    }

    /**
     * 字符串转换为日期
     *
     * @param dateString
     * @return
     */
    public static Date toDate(String dateString) {
        return toDate(dateString, "yyyy-MM-dd");
    }

    /**
     * 字符串转换为 年月日 时分秒
     *
     * @param dateString
     * @return
     */
    public static Date toDateTime(String dateString) {
        return toDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date toDate(String dateString, String format) {
        if (StringUtil.isEmpty(dateString)) return null;
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(dateString);
        } catch (Exception e) {
            LOGGER.equals("日期转换错误[" + dateString + "][" + format + "]");
        }
        return d;
    }

    /**
     * 获取昨天日期
     *
     * @return
     */
    public static Date getYeserday() {
        return new Date(getCurrentDate().getTime() - 24 * 60 * 60 * 1000);
    }

    public static String getYeserdayDate() {
        return format(getYeserday(), "yyyyMMdd");
    }

    public static String getYeserdayDateString() {
        return format(getYeserday(), "yyyy-MM-dd");
    }

    public static String getYeserdayDateTimeToString() {
        return format(getYeserday(), "yyyy-MM-dd 23:59:59");
    }

    public static String getYeserdayDateStartTimeToString(){return format(getYeserday(), "yyyy-MM-dd 00:00:00");}


    public static Date getYeserdayDateTimeToDate() {
        return toDate(format(getYeserday(), "yyyy-MM-dd 23:59:59"), YYYYMMDDHHMMSS);
    }


    /**
     * 日期比较,获取较大时间
     *
     * @param one
     * @param two
     * @return
     */
    public static Date maxDate(Date one, Date two) {
        return one.getTime() > two.getTime() ? one : two;
    }

    /**
     * 日期比较,获取较小时间
     *
     * @param one
     * @param two
     * @return
     */
    public static Date minDate(Date one, Date two) {
        return one.getTime() > two.getTime() ? two : one;
    }

    /*
    * 日期之间相差的月份
    * @Param date1: yyyy-MM-dd
    * @Param date2: yyyy-MM-dd
    * */
//    public static Integer betweenMonth(String date1, String date2) {
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//        DateTime start = formatter.parseDateTime(date1);
//        DateTime end = formatter.parseDateTime(date2);
//        int months = Months.monthsBetween(start, end).getMonths();
//        return months;
//    }

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr) {
         long day=0;
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
         Date beginDate;
         Date endDate;
         try
         {
                 beginDate = format.parse(beginDateStr);
                 endDate= format.parse(endDateStr);
                 day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
                 //System.out.println("相隔的天数="+day);
             } catch (ParseException e)
         {
                 // TODO 自动生成 catch 块
                 e.printStackTrace();
             }
         return day; }



    /**
     * 日期格式之间进行转换
     *
     * @param date
     * @param formatFrom
     * @param formatTo
     * @return
     */
    public static String formatDate(String date, String formatFrom, String formatTo) {

        DateFormat df = getNewDateFormat(formatFrom);
        String newDate = null;
        try {
            Date dt = df.parse(date);
            newDate = format(dt, formatTo);
        } catch (ParseException e) {
            return newDate;
        }

        return newDate;

    }

    /**
     * 得到新的时间格式
     * @param pattern   匹配表达式
     * @return          新的时间格式
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

}
