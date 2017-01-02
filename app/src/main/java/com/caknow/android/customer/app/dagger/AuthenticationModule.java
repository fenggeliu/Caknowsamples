package com.caknow.android.customer.app.dagger;

import com.caknow.android.customer.app.net.AuthenticationAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junu on 1/1/17.
 */

@Module(includes =  AppModule.class)
public class AuthenticationModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client){
        Gson gson = new GsonBuilder().create();
        return new Retrofit.Builder()
                .baseUrl(AuthenticationAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
