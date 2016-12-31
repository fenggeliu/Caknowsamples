package com.caknow.android.customer.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wesson_wxy on 2016/12/23.
 */

public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATA    = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to String
     * @param timeInMillis
     * @param dataFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dataFormat) {
        return dataFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to String, format is {@link #DEFAULT_DATE_FORMAT}
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis,DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time milliseconds
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     * @param dataFormat
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dataFormat) {
        return getTime(getCurrentTimeInLong(),dataFormat);
    }
}
