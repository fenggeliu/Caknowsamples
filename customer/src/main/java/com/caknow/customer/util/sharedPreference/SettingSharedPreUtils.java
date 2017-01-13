package com.caknow.customer.util.sharedPreference;

import android.content.Context;

import java.util.Map;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

public class SettingSharedPreUtils implements SharedPreferencesImpl{
    public static final String FILE_NAME = "CAKNOW_CUSTOMER_" + SettingSharedPreUtils.class.getSimpleName();
    public static final String KEY_WIFI_SWITCH = "CAKNOW_CUSTOMER_WIFI_SWITCH";
    public static final String KEY_GUIDE = "CAKNOW_CUSTOMER_GUIDE";

    private static volatile SettingSharedPreUtils mInstance;

    public static SettingSharedPreUtils getInstance() {
        if(mInstance == null) {
            synchronized (SettingSharedPreUtils.class) {
                if(mInstance == null) {
                    mInstance = new SettingSharedPreUtils();
                }
            }
        }
        return mInstance;
    }

    private SettingSharedPreUtils() {

    }


    @Override
    public void put(Context context,String key,Object object) {
        SharedPreferencesUtils.put(context,FILE_NAME,key,object);
    }

    @Override
    public Object get(Context context,String key,Object defaultObject) {
        return SharedPreferencesUtils.get(context,FILE_NAME,key,defaultObject);
    }

    @Override
    public void remove(Context context,String key) {
        SharedPreferencesUtils.remove(context,FILE_NAME,key);
    }

    @Override
    public void clear(Context context) {
        SharedPreferencesUtils.clear(context,FILE_NAME);
    }

    @Override
    public boolean contains(Context context,String key) {
        return SharedPreferencesUtils.contains(context,FILE_NAME,key);
    }

    @Override
    public Map<String,?> getAll(Context context) {
        return SharedPreferencesUtils.getAll(context,FILE_NAME);
    }

    public static boolean getWifiSwitchState(Context context,boolean isOnDefault) {
        return (Boolean) SettingSharedPreUtils.getInstance().get(context,KEY_WIFI_SWITCH,isOnDefault);
    }

    public static void setKeyWifiSwitchState(Context context,boolean isOn) {
        SettingSharedPreUtils.getInstance().put(context,KEY_WIFI_SWITCH,isOn);
    }

    public static boolean getGuideState(Context context,boolean isGuideDefault) {
        return (Boolean) SettingSharedPreUtils.getInstance().get(context,FILE_NAME,isGuideDefault);
    }

    public static void setGuideState(Context context,boolean isGuide) {
        SettingSharedPreUtils.getInstance().put(context,KEY_GUIDE,isGuide);
    }
}
