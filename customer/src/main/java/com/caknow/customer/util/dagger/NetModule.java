package com.caknow.customer.util.dagger;


import com.caknow.app.BuildConfig;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.BaseRequestInterceptor;
import com.cloudinary.Cloudinary;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junu on 1/1/17.
 */

@Module
public class NetModule {
    String baseUrl;

    public NetModule(String baseUrl){
        this.baseUrl = baseUrl;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        httpClient.addInterceptor(new BaseRequestInterceptor());
        if(BuildConfig.DEBUG){
            httpClient.addInterceptor(logging);
        }
        return httpClient.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient client){
        return  new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }


    @Provides
    @Singleton
    public Cloudinary provideCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "caknow");
        config.put("api_key", "471886552525434");
        config.put("api_secret", "nRQpYgAyqRT-jwYcc5CWxFuaStg");
        Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }


}
