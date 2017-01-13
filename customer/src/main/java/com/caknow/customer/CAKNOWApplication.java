package com.caknow.customer;

import android.content.Context;

import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.dagger.AppModule;
import com.caknow.customer.util.dagger.NetModule;
import com.facebook.stetho.Stetho;

/**
 * Created by junu on 1/1/17.
 */

public class CAKNOWApplication extends android.app.Application {

    private static CAKNOWApplication INSTANCE;

    private AppComponent appComponent;
    private NetComponent netComponent;


    public static CAKNOWApplication get(Context context) {
        return (CAKNOWApplication) context.getApplicationContext();
    }

    public static CAKNOWApplication get() {
        return CAKNOWApplication.INSTANCE;
    }

    public void onCreate() {
        super.onCreate();
        CAKNOWApplication.INSTANCE = this;
        Stetho.initializeWithDefaults(this);
        // this.appComponent = DaggerAppComponent.builder().build();
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://staging.caknow.com"))
                .build();
        SessionPreferences.INSTANCE.init(this);
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
