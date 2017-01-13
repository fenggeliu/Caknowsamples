package com.caknow.customer.util.sharedPreference;

/**
 * Created by wesson_wxy on 2016/12/21.
 */

import android.content.Context;

import java.util.Map;

/**
 * shared preferences interface
 */
public interface SharedPreferencesImpl {
    /**
     * save data method
     * call the different data type method based on the saved data type
     *
     * @param context
     * @param key
     * @param object
     */
    void put(Context context, String key, Object object);

    /**
     * get the data based on the saved data type
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return the data based on the saved data type
     */
    Object get(Context context, String key, Object defaultObject);

    /**
     * remove the pair of key
     *
     * @param context
     * @param key
     */
    void remove(Context context, String key);

    /**
     * remove all pair
     *
     * @param context
     */
    void clear(Context context);

    /**
     * search the key exist or not if exist return true else return false
     *
     * @param context
     * @param key
     * @return
     */
    boolean contains(Context context, String key);

    /**
     * return all pair
     *
     * @param context
     * @return
     */
    Map<String, ?> getAll(Context context);
}
