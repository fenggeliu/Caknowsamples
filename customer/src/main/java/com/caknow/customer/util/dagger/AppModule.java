package com.caknow.customer.util.dagger;

import com.caknow.customer.Application;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.BaseRequestInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junu on 1/1/17.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    Application provideApplication(){
        return this.application;
    }

}
