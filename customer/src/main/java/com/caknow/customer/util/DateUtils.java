package com.caknow.customer.util;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date util
 */

public class DateUtils {
    /**
     * 1s = 1000ms
     */
    private final static int TIME_MILLISECONDS = 1000;
    /**
     * 1minute = 60s
     */
    private final static int TIME_SECONDS = 60;
    /**
     * 1hour = 60minutes
     */
    private final static int TIME_MINUTES = 60;
    /**
     * 1day = 24hours
     */
    private final static int TIME_HOURS = 24;
    /**
     * date format
     */
    private final static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * month and date format
     */
    private final static String MD_FORMAT = "MM-dd";

    /**
     * get the time zone
     */
    public static TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * transfer string to Date
     *
     * @param date format string "yyyy-MM-dd HH:mm:ss"
     * @return Date expression of date string
     */
    public static Date parseDate(String date) {
        return parseDate(date, FORMAT);
    }

    /**
     * transfer string to Date
     *
     * @param date
     * @param format
     * @return Date expression
     */
    public static Date parseDate(String date, String format) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dt;
    }

    /**
     * transfer Date object to string
     *
     * @param date object
     * @return Date object string
     */
    public static String formatDate(Date date) {
        return formatDate(date, FORMAT);
    }

    /**
     * transfer Date object to String
     *
     * @param date   object
     * @param format string
     * @return Date expression string
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * Date format
     *
     * @param unixTime unixTime unix time stamp
     * @return string Date
     */
    public static String formatUnixTime(long unixTime) {
        return formatUnixTime(unixTime, FORMAT);
    }

    /**
     * Date format
     *
     * @param unixTime unix time stamp
     * @param format   string
     * @return Date string
     */
    public static String formatUnixTime(long unixTime, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(unixTime);
    }

    /**
     * GMT time transfer to default time zone string
     *
     * @param gmtUnixTime GMT Time stamp
     * @return Date string "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatGMTUnixTime(long gmtUnixTime) {
        return formatGMTUnixTime(gmtUnixTime, FORMAT);
    }

    /**
     * GMT time transfer to default time zone string
     *
     * @param gmtUnixTime GMT time stamp
     * @param format      string
     * @return Date string "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatGMTUnixTime(long gmtUnixTime, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(gmtUnixTime + TimeZone.getDefault().getRawOffset());
    }

    /**
     * get the time stamp
     *
     * @param unixTime unix time stamp
     * @return Date object
     */
    public static Date getDate(long unixTime) {
        return new Date(unixTime);
    }

    /**
     * get GMT time stamp
     *
     * @param gmtUnixTime GMT Unix time stamp
     * @return Date object
     */
    public static Date getGMTDate(long gmtUnixTime) {
        return new Date(gmtUnixTime + TimeZone.getDefault().getRawOffset());
    }

    /**
     * default Unix time stamp transfer to GMT Unix time stamp
     *
     * @param unixTime unix time stamp
     * @return GMT Unix time stamp
     */
    public static long getGMTUnixTime(long unixTime) {
        return unixTime - TimeZone.getDefault().getRawOffset();
    }

    /**
     * GMT Unix time stamp transfer to default Unix time stamp
     *
     * @param gmtUnixTime GMT Unix time stamp
     * @return default unix time stamp
     */
    public static long getCurrentTimeZoneUnixTime(long gmtUnixTime) {
        return gmtUnixTime + TimeZone.getDefault().getRawOffset();
    }

    /**
     * get current GMT Unix time stamp
     *
     * @return current GMT Unix time stamp
     */
    public static long getGMTUnixTimeByCalendar() {
        Calendar calendar = Calendar.getInstance();
        // get current time zone date time stamp
        long unixTime = calendar.getTimeInMillis();
        // get GMT date time stamp
        long unixTimeGMT = unixTime - TimeZone.getDefault().getRawOffset();

        return unixTimeGMT;
    }

    /**
     * get current unix time stamp
     *
     * @return current unix time stamp
     */
    public static long getUnixTimeByCalendar() {
        Calendar calendar = Calendar.getInstance();
        //get current time zone time stamp
        long unixTime = calendar.getTimeInMillis();
        return unixTime;
    }

    /**
     * get time by change time zone
     *
     * @param date    time
     * @param oldZone
     * @param newZone
     * @return time
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }

    /**
     * get month and date
     *
     * @param unixTime time stamp
     * @return new month and date
     */
    public static String getMDFormat(long unixTime) {
        return getMDFormat(unixTime, MD_FORMAT);
    }

    /**
     * get month and date
     *
     * @param unixTime time stamp
     * @param format   string
     * @return new month and date
     */
    public static String getMDFormat(long unixTime, String format) {
        String time = formatGMTUnixTime(unixTime, format);
        time.replace("01-", "Jan ");
        time.replace("02-", "Feb ");
        time.replace("03-", "Mar ");
        time.replace("04-", "Apr ");
        time.replace("05-", "May ");
        time.replace("06-", "Jun ");
        time.replace("07-", "Jul ");
        time.replace("08-", "Aug ");
        time.replace("09-", "Sep ");
        time.replace("10-", "Oct ");
        time.replace("11-", "Nov ");
        time.replace("12-", "Dec ");

        return time;
    }

    /**
     * use seconds to implement hh:mm:ss
     *
     * @param seconds
     * @return hh:mm:ss
     */
    public static String formatTime(long seconds) {
        long hh = seconds / TIME_SECONDS / TIME_MINUTES;
        long mm = (seconds - hh * TIME_MINUTES * TIME_SECONDS) > 0 ? (seconds - hh * TIME_MINUTES * TIME_SECONDS) / TIME_SECONDS : 0;
        long ss = seconds < TIME_SECONDS ? seconds : seconds % TIME_SECONDS;

        return (hh == 0 ? "" : (hh < 10 ? "0" + hh : hh) + "h")
                + (mm == 0 ? "" : (mm < 10 ? "0" + mm : mm) + "m")
                + (ss == 0 ? "" : (ss < 10 ? "0" + ss : ss) + "s");
    }

    /**
     * get minus value between current time and create time
     *
     * @param date long date
     * @return minus value
     */
    public static String getDiffTime(long date) {
        String strTime = "n days ago";
        long time = Math.abs(new Date().getTime() - date);
        // 1 minute ago
        if (time < TIME_SECONDS * TIME_MILLISECONDS) {
            strTime = "Current";
        } else {
            int min = (int) (time / TIME_MILLISECONDS / TIME_SECONDS);
            if (min < TIME_SECONDS) {
                if (min < 15) {
                    strTime = "15 minutes ago";
                } else if (min < 30) {
                    strTime = "half an hour ago";
                } else {
                    strTime = "one hour ago";
                }
            } else {
                int hh = min / TIME_HOURS;
                if (hh < TIME_HOURS) {
                    strTime = hh + "hours ago";
                } else {
                    int days = hh / TIME_HOURS;
                    if (days <= 6) {
                        strTime = days + "days ago";
                    } else {
                        int weeks = days / 7;
                        if (weeks < 3) {
                            strTime = weeks + "weeks ago";
                        }
                    }
                }
            }
        }

        return strTime;
    }

}
