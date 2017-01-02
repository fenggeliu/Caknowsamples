package com.caknow.android.customer.app;

import com.caknow.android.customer.app.dagger.AppModule;
import com.caknow.android.customer.app.dagger.AuthenticationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by junu on 1/1/17.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                AuthenticationModule.class
        }
)
public interface AppComponent {

//    SplashActivityComponent plus(SplashActivityModule module);
//
//    UserComponent plus(UserModule userModule);

}