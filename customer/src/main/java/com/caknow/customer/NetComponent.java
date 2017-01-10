package com.caknow.customer;

import com.caknow.customer.garage.NewVehicleActivity;
import com.caknow.customer.garage.fragment.GarageFragment;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.dagger.AppModule;
import com.caknow.customer.util.dagger.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by junu on 1/1/17.
 */

@Singleton
@Component(
        modules = {
                AppModule.class, NetModule.class
        }
)
public interface NetComponent {


        void inject(HomeActivity mainActivity);

        void inject(GarageFragment garageFragment);

        void inject(NewVehicleActivity newVehicleActivity);
}