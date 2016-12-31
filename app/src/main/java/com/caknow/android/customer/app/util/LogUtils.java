package com.caknow.android.customer.app.util;

import android.util.Log;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

public class LogUtils {
    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    public static boolean isDebug = true;
    private static final String TAG = "DefaultTag";

    /**
     * default Log i
     * @param msg
     */
    public static void Log_i(String msg) {
        if(isDebug){
            Log.i(TAG,msg);
        }
    }

    /**
     * default Log d
     * @param msg
     */
    public static void Log_d(String msg) {
        if(isDebug) {
            Log.d(TAG,msg);
        }
    }

    /**
     * default Log e
     * @param msg
     */
    public static void Log_e(String msg) {
        if(isDebug) {
            Log.e(TAG,msg);
        }
    }

    /**
     * default Log v
     * @param msg
     */
    public static void Log_v(String msg) {
        if(isDebug) {
            Log.v(TAG,msg);
        }
    }

    /**
     * custom Log i
     * @param tag
     * @param msg
     */
    public static void Log_i(String tag,String msg) {
        if(isDebug){
            Log.i(tag,msg);
        }
    }

    /**
     * custom Log d
     * @param tag
     * @param msg
     */
    public static void Log_d(String tag,String msg) {
        if(isDebug) {
            Log.d(tag, msg);
        }
    }

    /**
     * custom Log e
     * @param tag
     * @param msg
     */
    public static void Log_e(String tag,String msg) {
        if(isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * custom Log v
     * @param tag
     * @param msg
     */
    public static void Log_v(String tag,String msg) {
        if(isDebug) {
            Log.v(tag, msg);
        }
    }
}
