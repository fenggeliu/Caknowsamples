package com.caknow.customer;

import com.caknow.customer.util.dagger.AppModule;
import com.caknow.customer.util.dagger.AuthenticationModule;

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