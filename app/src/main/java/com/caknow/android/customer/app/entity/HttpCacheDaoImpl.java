package com.caknow.android.customer.app.entity;

import java.util.Map;

/**
 * Created by wesson_wxy on 2016/12/28.
 */

public interface HttpCacheDaoImpl {

    /**
     * insert httpResponse
     * @param httpResponse
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    long insertHttpResponse(HttpResponse httpResponse);

    /**
     * get HttpResponse by url
     * @param url
     * @return
     */
    HttpResponse getHttpResponse(String url);

    /**
     * get HttpResponses by type
     * @param type
     * @return
     */
    Map<String, HttpResponse> getHttpResponsesByType(int type);

    /**
     * delete all http response
     * @return
     */
    int deleteAllHttpResponse();
}
