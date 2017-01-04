package com.caknow.customer.util.dagger;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by junu on 1/1/17.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("x-api-key", "sJvVmx9uyJD7eE1bZraPEUfsm6BpzyOlgDZ04eqRyUs="); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }
}
