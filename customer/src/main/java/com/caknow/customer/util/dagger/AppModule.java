package com.caknow.customer.util.dagger;

import com.caknow.customer.CAKNOWApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by junu on 1/1/17.
 */

@Module
public class AppModule {
    CAKNOWApplication CAKNOWApplication;

    public AppModule(CAKNOWApplication CAKNOWApplication) {
        this.CAKNOWApplication = CAKNOWApplication;
    }

    @Provides
    CAKNOWApplication provideApplication() {
        return this.CAKNOWApplication;
    }

}
