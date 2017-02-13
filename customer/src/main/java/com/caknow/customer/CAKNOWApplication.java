package com.caknow.customer;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.dagger.AppModule;
import com.caknow.customer.util.dagger.NetModule;
import com.caknow.customer.widget.AppComponent;
import com.caknow.customer.widget.DaggerNetComponent;
import com.caknow.customer.widget.NetComponent;
import com.facebook.stetho.Stetho;

/**
 * Created by junu on 1/1/17.
 */

public class CAKNOWApplication extends MultiDexApplication {

    private static CAKNOWApplication INSTANCE;

    private AppComponent appComponent;
    private NetComponent netComponent;


    public static CAKNOWApplication get(Context context){
        return (CAKNOWApplication) context.getApplicationContext();
    }

    public static CAKNOWApplication get(){
        return CAKNOWApplication.INSTANCE;
    }

    /**
     * To switch endpoints first construct a new netmodule with the new endpoint. then go to BaseRequestInterceptor and swap out API keys
     */
    public void onCreate() {
        super.onCreate();
        CAKNOWApplication.INSTANCE = this;
        Stetho.initializeWithDefaults(this);
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constants.STAGING_ENDPOINT))
                //.netModule(new NetModule(Constants.STAGING_ENDPOINT))
                .build();
        SessionPreferences.INSTANCE.init(this);
    }

    public NetComponent getNetComponent() { return netComponent; }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        MultiDex.install(newBase);
        super.attachBaseContext(newBase);
    }
}
