package com.caknow.android.customer.app.entity;

/**
 * Created by wesson_wxy on 2016/12/22.
 */


import com.caknow.android.customer.app.util.HttpUtils;

import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * <strong>HttpRequest</strong><br/>
 * <ul>
 *     <strong>Constructor</strong>
 *     <li>{@link HttpRequest#HttpRequest(String)}</li>
 *     <li>{@link HttpRequest#HttpRequest(String, Map)}</li>
 * </ul>
 * <ul>
 *     <strong>Setting</strong>
 *     <li>{@link #setConnectTimeout(int)}</li>
 *     <li>{@link #setReadTimeout(int)}</li>
 *     <li>{@link #setParasMap(Map)}</li>
 *     <li>{@link #setUserAgent(String)}</li>
 *     <li>{@link #setRequestProperty(String, String)}</li>
 *     <li>{@link #setRequestProperties(Map)}</li>
 * </ul>
 */
public class HttpRequest {
    private String              url;
    private int                 connectTimeout;
    private int                 readTimeout;
    private Map<String,String>  parasMap;
    private Map<String,String>  requestProperties;

    /**
     * Http Request
     * @param url
     */
    public HttpRequest(String url) {
        this.url = url;
        this.connectTimeout = -1;
        this.readTimeout = -1;
        requestProperties = new HashMap<String,String>();
    }

    /**
     * Http Request
     * @param url
     * @param parasMap
     */
    public HttpRequest(String url, Map<String,String> parasMap) {
        this.url = url;
        this.parasMap = parasMap;
        this.connectTimeout = -1;
        this.readTimeout = -1;
        requestProperties = new HashMap<String,String>();
    }

    /**
     * get the url
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * get the connect time out
     * @return
     * @see URLConnection#getConnectTimeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * set connect time out
     * @param timeoutMillis
     * @see URLConnection#setConnectTimeout
     */
    public void setConnectTimeout(int timeoutMillis) {
        if(timeoutMillis < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        connectTimeout = timeoutMillis;
    }

    /**
     * get the read time out
     * @return
     * @see URLConnection#getReadTimeout()
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * set read time out
     * @param timeoutMillis
     * @see URLConnection#setReadTimeout(int)
     */
    public void setReadTimeout(int timeoutMillis) {
        if(timeoutMillis < 0){
            throw new IllegalArgumentException("timeout can not be negative");
        }
        readTimeout = timeoutMillis;
    }

    /**
     * get paras map
     * @return
     */
    public Map<String,String> getParasMap() {
        return parasMap;
    }

    /**
     * set paras map
     * @param parasMap
     */
    public void setParasMap(Map<String,String> parasMap) {
        this.parasMap = parasMap;
    }

    /**
     * paras transfer to String
     * @return paras as String
     */
    public String getParas(){
        return HttpUtils.joinParasWithEncodeValue(parasMap);
    }

    /**
     *
     * @param field
     * @param newValue
     * @see URLConnection#setRequestProperty(String, String)
     */
    public void setRequestProperty(String field,String newValue){
        requestProperties.put(field,newValue);
    }

    /**
     *
     * @param field
     * @return
     * @see URLConnection#getRequestProperty(String)
     */
    public String getRequestProperty(String field) {
        return requestProperties.get(field);
    }

    /**
     * same to {@link #setRequestProperty(String, String)} filed is User-Agent
     * @param value
     * @see URLConnection#setRequestProperty(String, String)
     */
    public void setUserAgent(String value) {
        requestProperties.put("User-Agent",value);
    }

    /**
     * get request property
     * @return
     */
    public Map<String,String> getRequestProperties() {
        return requestProperties;
    }

    /**
     * set request property
     * @param requestProperties
     */
    public void setRequestProperties(Map<String,String> requestProperties) {
        this.requestProperties = requestProperties;
    }

}
