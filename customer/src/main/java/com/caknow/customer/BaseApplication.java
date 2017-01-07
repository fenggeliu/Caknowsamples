package com.caknow.customer;

import android.app.Application;
import android.content.Context;

import com.caknow.customer.util.SessionPreferences;
import com.facebook.stetho.Stetho;

/**
 * Created by junu on 1/1/17.
 */

public class BaseApplication extends Application {

    private static BaseApplication INSTANCE;

    private AppComponent appComponent;
//    private UserComponent userComponent;

    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }

    public static BaseApplication get(){
        return BaseApplication.INSTANCE;
    }
    public void onCreate() {
        super.onCreate();
        BaseApplication.INSTANCE = this;
        Stetho.initializeWithDefaults(this);
        this.appComponent = DaggerAppComponent.builder().build();
        SessionPreferences.INSTANCE.init(this);
    }


    public AppComponent getAppComponent(){
        return appComponent;
    }


}
