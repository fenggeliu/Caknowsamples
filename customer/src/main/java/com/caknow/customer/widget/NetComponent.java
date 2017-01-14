package com.caknow.customer.widget;

import com.caknow.customer.job.JobActivity;
import com.caknow.customer.registration.LoginActivity;
import com.caknow.customer.garage.AddVehicleActivity;
import com.caknow.customer.garage.VehicleServiceActivity;
import com.caknow.customer.history.HistoryActivity;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.payment.PaymentActivity;
import com.caknow.customer.registration.SignUpActivity;
import com.caknow.customer.registration.VerificationActivity;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.settings.ManageGarageAdapter;
import com.caknow.customer.settings.SettingsActivity;
import com.caknow.customer.transaction.TransactionActivity;
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

        void inject(AddVehicleActivity newVehicleActivity);

        void inject(ManageGarageAdapter manageGarageAdapter);

        void inject(BaseFragment baseFragment);

        void inject(PaymentActivity paymentActivity);

        void inject(NewServiceRequestActivity newServiceRequestActivity);

        void inject(VehicleServiceActivity vehicleServiceActivity);

        void inject(LoginActivity loginActivity);

        void inject(VerificationActivity verificationActivity);

        void inject(SignUpActivity signUpActivity);

        void inject(SettingsActivity settingsActivity);

        void inject(HistoryActivity historyActivity);

        void inject(JobActivity jobActivity);

        void inject(TransactionActivity transactionActivity);
}