package com.caknow.customer.util.net;

import com.caknow.customer.util.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by junu on 12/31/16.
 */
public class BaseRequestInterceptor implements Interceptor {


    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;

        newRequest = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader(HeadersContract.HEADER_X_API_KEY, "sJvVmx9uyJD7eE1bZraPEUfsm6BpzyOlgDZ04eqRyUs=")
                .addHeader(HeadersContract.HEADER_X_ACCESS_TOKEN, SessionPreferences.INSTANCE.getStringPref(PreferenceKeys.ACCESS_TOKEN))
                .build();
        return chain.proceed(newRequest);
    }
}