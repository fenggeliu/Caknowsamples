package com.caknow.android.customer.app.entity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.caknow.android.customer.app.constant.HttpConstants;
import com.caknow.android.customer.app.util.ArrayUtils;
import com.caknow.android.customer.app.util.HttpUtils;
import com.caknow.android.customer.app.util.SqliteUtils;
import com.caknow.android.customer.app.util.StringUtils;
import com.caknow.android.customer.app.util.SystemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by wesson_wxy on 2016/12/27.
 */

public class HttpCache {

    private Context                    context;


    /** http memory cache **/
    private Map<String, HttpResponse>  cache;
    /** dao to get data from http db cache **/
    private HttpCacheDaoImpl httpCacheDaoImpl;
    private int                        type           = -1;
    /** Default {@link Executor} that be used to execute tasks in parallel **/
    public static final Executor THREAD_POOL_EXECUTOR = Executors.newFixedThreadPool(SystemUtils.DEFAULT_THREAD_POOL_SIZE);

    /**
     * constructor of http cache with context parameter
     * @param context
     */
    public HttpCache(Context context) {
        if(context == null) {
            throw new IllegalArgumentException("The context can not be null.");
        }
        this.context = context;
        cache = new ConcurrentHashMap<String, HttpResponse>();
        httpCacheDaoImpl = new HttpCacheDao(SqliteUtils.getInstance(context));
    }

    /**
     * constructor of http cache with context and type parameter
     * @param context
     * @param type
     */
    public HttpCache(Context context, int type) {
        this(context);
        this.type = type;
        initData(type);
    }

    /**
     * get httpResponse whose type is type into memory as primary cache to improve performance
     * @param type
     */
    private void initData(int type) {
        this.cache = httpCacheDaoImpl.getHttpResponsesByType(type);
        if(cache == null) {
            cache = new HashMap<String, HttpResponse>();
        }
    }

    /**
     * http get
     * <ul>
     *     <strong>Attentions:</strong>
     *     <li>Don't call this on the ui thread, it may costs some times.Because if not in cache, it get from network synchronous</li>
     *     <li>If you want get data asynchronous, use {@link HttpCache#httpGet(HttpRequest,HttpCacheListener)}</li>
     * </ul>
     * @param request
     * @return the response of the url, if null represents http error
     */
    public HttpResponse httpGet(HttpRequest request) {
        String url;
        if(request == null || StringUtils.isEmpty(url = request.getUrl())) {
            return null;
        }

        HttpResponse cacheResponse = null;
        boolean isNoCache = false;
        boolean isNoStore = false;
        String requestCacheControl = request.getRequestProperty(HttpConstants.CACHE_CONTROL);
        if(!StringUtils.isEmpty(requestCacheControl)) {
            String[] requestCacheControls = requestCacheControl.split(",");
            if(!ArrayUtils.isEmpty(requestCacheControls)) {
                List<String> requestCacheControlList = new ArrayList<String>();
                for(String s : requestCacheControls) {
                    if(s == null) {
                        continue;
                    }
                    requestCacheControlList.add(s.trim());
                }

                if(requestCacheControlList.contains("no-cache")) {
                    isNoCache = true;
                }

                if(requestCacheControlList.contains("no-store")) {
                    isNoStore = true;
                }
            }
        }

        if(!isNoCache) {
            cacheResponse = getFromCache(url);
        }
        return cacheResponse == null ? (isNoStore ? HttpUtils.httpGet(url) : putIntoCache(HttpUtils.httpGet(url))) : cacheResponse;
    }

    /**
     * http get
     * <ul>
     *     <li>It gets data from cache or network asynchronous.</li>
     *     <li>If you want to get daa synchronous, use {@link HttpCache#httpGet(HttpRequest)} or {@link HttpCache#httpGetString(HttpRequest)}</li>
     * </ul>
     * @param url
     * @param listener listener which can do something before or after HttpGet. this can be null if you not want to something
     */
    public void httpGet(String url,HttpCacheListener listener) {
        // if bigger than android 4.0 use executeOnExecutor, else use execute
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            new HttpCacheStringAsyncTask(listener).executeOnExecutor(THREAD_POOL_EXECUTOR,url);
        } else {
            new HttpCacheStringAsyncTask(listener).execute(url);
        }
    }

    /**
     * http get
     * <ul>
     *     <li>It gets data from cache or network asynchronous.</li>
     *     <li>If you want get data synchronous, use {@link HttpCache#httpGet(HttpRequest)} or {@link HttpCache#httpGetString(HttpRequest)}</li>
     * </ul>
     * @param request
     * @param listener
     */
    public void httpGet(HttpRequest request,HttpCacheListener listener) {
        // if bigger than android 4.0 use executeOnExecutor, else use execute
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            new HttpCacheRequestAsyncTask(listener).executeOnExecutor(THREAD_POOL_EXECUTOR,request);
        } else {
            new HttpCacheRequestAsyncTask(listener).execute(request);
        }
    }

    /**p
     * http get
     * <ul>
     *     <strong>Attentions:</strong>
     *     <li>Don't call this on the ui thread, it may costs some times. Because if not in cache, it get from network synchronous.</li>
     *     <li>If you want get data asynchronous, use {@link HttpCache#httpGet(HttpRequest,HttpCacheListener)}</li>
     * </ul>
     * @param url
     * @return the response of the url, if null represents http error
     */
    public HttpResponse httpGet(String url) {
        return httpGet(new HttpRequest(url));
    }

    /**
     * http get
     * <ul>
     *     <strong>Attentions:</strong>
     *     <li>Don't call this on the ui thread, it may costs some times. Because if not in cache, it get from network synchronous</li>
     *     <li>If you want get data asynchronous, use {@link HttpCache#httpGet(HttpRequest, HttpCacheListener)}</li>
     * </ul>
     * @param url
     * @return
     */
    public String httpGetString(String url) {
        HttpResponse cacheResponse = httpGet(new HttpRequest(url));
        return cacheResponse == null ? null : cacheResponse.getResponseBody();
    }

    /**
     * http get
     * <ul>
     *     <strong>Attentions:</strong>
     *     <li>Don't call this on the ui thread, it may costs some times. Because if not in cache, it get from network synchronous.</li>
     *     <li>If you want get data asynchronous, use {@link HttpCache#httpGet(HttpRequest, HttpCacheListener)}</li>
     * </ul>
     * @param httpRequest
     * @return the response body of the url, if null represents http error
     */
    public HttpResponse httpGetString(HttpRequest httpRequest) {
        return httpGet(httpRequest);
    }

    /**
     * whether this cache contains the specified url.
     * @param url
     * @return true if this cache contains the specified url and the element is valid, false otherwise
     */
    public boolean containsKey(String url) {
        return getFromCache(url) != null;
    }

    /**
     * whether the element of the specified url has invalided
     * @param url
     * @return true if the element of the specified url has invalided, false otherwise
     */
    protected boolean isExpired(String url) {
        return getFromCache(url) == null;
    }

    /**
     * Removes all elements from this cache, leaving it empty.
     */
    public void clear() {
        cache.clear();
//        httpCacheDao.deleteAllHttpResponse();
    }

    /**
     * get the type
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * HttpCacheListener, can do something before or after HttpGet
     */
    public static abstract class HttpCacheListener {
        /**
         * Runs on the UI thread after httpGet. The httpResponse is returned by httpGet.
         * <ul>
         *     <li>this can be null if you don't want to do something</li>
         * </ul>
         */
        protected void onPreGet() {}

        /**
         * Runs on the UI thread after httpGet. The httpResponse is returned by httpGet.
         * <ul>
         *     <li>this can be null if you don't want to do something</li>
         * </ul>
         * @param httpResponse get by the url
         * @param isInCache isInCache the data response to the url whether is in cache
         */
        protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {}
    }

    /**
     * put response into cache
     * <ul>
     *     <li>put response to db,if {@link HttpResponse#getType()} == {@link HttpCache#getType()}, also put into memory cache</li>
     * </ul>
     * @param httpResponse
     * @return if insert into db error, return null, otherwise return HttpResponse
     */
    private HttpResponse putIntoCache(HttpResponse httpResponse) {
        String url;

        if(httpResponse == null || (url = httpResponse.getUrl()) == null) {
            return null;
        }

        if(type != -1 && type == httpResponse.getType()) {
            cache.put(url,httpResponse);
        }
        return (httpCacheDaoImpl.insertHttpResponse(httpResponse) == -1) ? null : httpResponse;
    }

    /**
     * get from memory cache first, if not exist in memory cache, get from db
     * @param url
     * @return <ul>
     *     <li>if neither exist in memory cache nor db, return null</li>
     *     <li>if expired, return null, otherwise return cache response</li>
     * </ul>
     */
    public HttpResponse getFromCache(String url) {
        if(StringUtils.isEmpty(url)) {
            return null;
        }

        HttpResponse cacheResponse = cache.get(url);
        if(cacheResponse == null) {
            cacheResponse = httpCacheDaoImpl.getHttpResponse(url);
        }
        return (cacheResponse == null || cacheResponse.isExpired()) ? null : cacheResponse.setInCache(true);
    }

    /**
     * AsyncTask to get data by String url
     */
    private class HttpCacheStringAsyncTask extends AsyncTask<String,Void,HttpResponse> {

        private HttpCacheListener listener;

        public HttpCacheStringAsyncTask(HttpCacheListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(String... url) {
            if(ArrayUtils.isEmpty(url)) {
                return null;
            }
            return httpGet(url[0]);
        }

        protected void onPreExecute() {
            if(listener != null) {
                listener.onPreGet();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if(listener != null) {
                listener.onPostGet(httpResponse, httpResponse != null && httpResponse.isInCache());
            }
        }
    }

    /**
     * AsyncTask to get data by HttpRequest
     */
    private class HttpCacheRequestAsyncTask extends AsyncTask<HttpRequest, Void, HttpResponse> {

        private HttpCacheListener listener;

        public HttpCacheRequestAsyncTask(HttpCacheListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(HttpRequest... httpRequest) {
            if(ArrayUtils.isEmpty(httpRequest)) {
                return null;
            }
            return httpGet(httpRequest[0]);
        }

        protected void onPreExecute() {
            if(listener != null) {
                listener.onPreGet();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if(listener != null) {
                listener.onPostGet(httpResponse, httpResponse != null && httpResponse.isInCache());
            }
        }
    }
}
