package com.caknow.android.customer.app.util.sharedPreference;

import android.content.Context;

import java.util.Map;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

public class UserInfoSharedPreUtils implements SharedPreferencesImpl{
    public static final String FILE_NAME = "CAKNOW_CUSTOMER_" + UserInfoSharedPreUtils.class.getSimpleName();

    private static volatile UserInfoSharedPreUtils mInstance;

    public UserInfoSharedPreUtils getInstance() {
        if(mInstance == null) {
            synchronized (UserInfoSharedPreUtils.class) {
                if(mInstance == null) {
                    mInstance = new UserInfoSharedPreUtils();
                }
            }
        }
        return mInstance;
    }

    private UserInfoSharedPreUtils() {

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
}
