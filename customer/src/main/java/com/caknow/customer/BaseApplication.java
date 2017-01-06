package com.caknow.customer;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by junu on 1/1/17.
 */

public class BaseApplication extends Application {


    private AppComponent appComponent;
//    private UserComponent userComponent;

    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        this.appComponent = DaggerAppComponent.builder().build();
    }


    public AppComponent getAppComponent(){
        return appComponent;
    }


}
