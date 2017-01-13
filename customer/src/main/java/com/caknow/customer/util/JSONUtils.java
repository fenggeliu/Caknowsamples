package com.caknow.customer.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wesson_wxy on 2016/12/23.
 */

public class JSONUtils {
    public static boolean isPrintException = true;

    private JSONUtils() {
        throw new AssertionError();
    }

    /**
     * get Long from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if key is null or empty, return defaultValue</li>
     *     <li>if {@link JSONObject#getLong(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONObject#getLong(String)}</li>
     * </ul>
     */
    public static Long getLong(JSONObject jsonObject, String key, Long defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getLong(key);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Long from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>jsonObject is null, return defaultValue</li>
     *     <li>if jsonData {@link JSONObject#JSONObject(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONUtils#getLong(JSONObject, String, Long)}</li>
     * </ul>
     */
    public static Long getLong(String jsonData,String key,Long defaultValue){
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            return getLong(jsonObject,key,defaultValue);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getLong(JSONObject, String, Long)
     */
    public static long getLong(JSONObject jsonObject,String key,long defaultValue) {
        return getLong(jsonObject,key,(Long)defaultValue);
    }

    /**
     *
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getLong(String,String,Long)
     */
    public static long getLong(String jsonData,String key,long defaultValue) {
        return getLong(jsonData,key,(Long)defaultValue);
    }

    /**
     * get Int from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if key is null or empty, return defaultValue</li>
     *     <li>if {@link JSONObject#getInt(String)}</li>
     * </ul>
     */
    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try{
            return jsonObject.getInt(key);
        }catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Int from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if jsonData {@link JSONObject#JSONObject(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONUtils#getInt(JSONObject, String, Integer)}</li>
     * </ul>
     */
    public static Integer getInt(String jsonData, String key, Integer defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            return getInt(jsonObject,key,defaultValue);
        }catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getInt(JSONObject, String, Integer)
     */
    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        return getInt(jsonObject,key,(Integer)defaultValue);
    }

    /**
     *
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getInt(String, String, Integer)
     */
    public static int getInt(String jsonData, String key, int defaultValue) {
        return getInt(jsonData,key,(Integer)defaultValue);
    }

    /**
     * get Double from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if key is null or empty, return defaultValue</li>
     *     <li>if {@link JSONObject#getDouble(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONObject#getDouble(String)}</li>
     * </ul>
     */
    public static Double getDouble(JSONObject jsonObject, String key, Double defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try{
            return jsonObject.getDouble(key);
        }catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Double from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     */
    public static Double getDouble(String jsonData, String key, Double defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            return getDouble(jsonObject,key,defaultValue);
        }catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getDouble(JSONObject, String, Double)
     */
    public static Double getDouble(JSONObject jsonObject, String key, double defaultValue) {
        return getDouble(jsonObject,key,(Double)defaultValue);
    }

    /**
     *
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     * @see JSONUtils#getDouble(String,String,Double)
     */
    public static Double getDouble(String jsonData, String key, double defaultValue) {
        return getDouble(jsonData,key,(Double)defaultValue);
    }

    /**
     * get string from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if key is null or empty, return defaultValue</li>
     *     <li>if {@link JSONObject#getString(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONObject#getString(String)}</li>
     * </ul>
     */
    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try{
            return jsonObject.getString(key);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get string from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if jsonData {@link JSONObject#getString(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONUtils#getString(JSONObject, String, String)}</li>
     * </ul>
     */
    public static String getString(String jsonData, String key, String defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getString(jsonObject, key, defaultValue);
        }catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get string from jsonObject
     * @param jsonObject
     * @param defaultValue
     * @param keyArray
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if keyArray is null or empty, return defaultValue</li>
     *     <li>get {@link #getJSONObject(JSONObject, String, JSONObject)} by recursion, return it. if anyone is null, return directly</li>
     * </ul>
     */
    public static String getStringCascade(JSONObject jsonObject,String defaultValue,String... keyArray) {
        if(jsonObject == null || ArrayUtils.isEmpty(keyArray)) {
            return defaultValue;
        }

        String data = jsonObject.toString();
        for(String key : keyArray) {
            data = getStringCascade(data,key,defaultValue);
            if (data == null) {
                return defaultValue;
            }
        }

        return data;
    }

    /**
     * get string from jsonData
     * @param jsonData
     * @param defaultValue
     * @param keyArray
     * @return <ul>
     *     <li>if jsonData is null, return defaultValue</li>
     *     <li>if keyArray is null or empty, return defaultValue</li>
     *     <li>get {@link #getJSONObject(JSONObject, String, JSONObject)} by recursion, return it. if anyone is null, return directly</li>
     * </ul>
     */
    public static String getStringCascade(String jsonData, String defaultValue, String... keyArray) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        String data = jsonData;
        for(String key:keyArray) {
            data = getString(data,key,defaultValue);
            if(data == null) {
                return defaultValue;
            }
        }
        return data;
    }


    /**
     * get JSONObject from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if key is null or empty, return defaultValue</li>
     *     <li>if {@link JSONObject#getJSONObject(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONObject#getJSONObject(String)}</li>
     * </ul>
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONObject(key);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonData is null, return defaultValue</li>
     *     <li>if jsonData {@link JSONObject#JSONObject(String)} exception, return defaultValue</li>
     *     <li>return {@link JSONUtils#getJSONObject(JSONObject, String, JSONObject)}</li>
     * </ul>
     */
    public static JSONObject getJSONObject(String jsonData, String key, JSONObject defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONObject(jsonObject,key,defaultValue);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonObject
     * @param jsonObject
     * @param defaultValue
     * @param keyArray
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if keyArray is null or empty, return defaultValue</li>
     *     <li>get {@link #getJSONObject(JSONObject, String, JSONObject)} by recursion, return it. if anyone is null, return directly</li>
     * </ul>
     */
    public static JSONObject getJSONObjectCascade(JSONObject jsonObject, JSONObject defaultValue, String... keyArray) {
        if(jsonObject == null || ArrayUtils.isEmpty(keyArray)) {
            return defaultValue;
        }

        JSONObject js = jsonObject;
        for(String key : keyArray) {
            js = getJSONObject(js,key,defaultValue);
            if(js == null) {
                return defaultValue;
            }
        }
        return js;
    }

    /**
     * get JSONObject from jsonData
     * @param jsonData
     * @param defaultValue
     * @param keyArray
     * @return <ul>
     *     <li>if jsonData is null, return defaultValue</li>
     *     <li>if keyArray is null or empty, return defaultValue</li>
     *     <li>get {@link #getJSONObject(JSONObject, String, JSONObject)} by recursion, return it. if anyone is null, return directly</li>
     * </ul>
     */
    public static JSONObject getJSONObjectCascade(String jsonData, JSONObject defaultValue, String... keyArray) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONObjectCascade(jsonObject,defaultValue,keyArray);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONArray from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONArray(key);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONArray from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if jsonData {@link JSONObject#JSONObject(String)}</li>
     *     <li>return {@link JSONUtils#getJSONArray(JSONObject, String, JSONArray)}</li>
     * </ul>
     */
    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONArray(jsonObject,key,defaultValue);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Boolean from jsonObject
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if key is null or empty, return defaultVale</li>
     *     <li>return {@link JSONObject#getBoolean(String)}</li>
     * </ul>
     */
    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if(jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getBoolean(key);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get boolean from jsonData
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return <ul>
     *     <li>if jsonObject is null, return defaultValue</li>
     *     <li>if jsonData {@link JSONObject#JSONObject(String)}</li>
     *     <li>return {@link JSONUtils#getBoolean(JSONObject, String, Boolean)}</li>
     * </ul>
     */
    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getBoolean(jsonObject,key,defaultValue);
        } catch(JSONException e) {
            if(isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }
}
