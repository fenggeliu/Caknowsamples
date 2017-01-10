package com.caknow.customer.garage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.garage.fragment.AddVehicleFragment;
import com.caknow.customer.garage.fragment.ConfirmVehicleFragment;
import com.caknow.customer.garage.fragment.GarageFragment;
import com.caknow.customer.garage.fragment.VehicleServiceFragment;
import com.caknow.customer.util.constant.Constants;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class VehicleServiceActivity extends BaseActivity implements VehicleServiceFragment.OnListFragmentInteractionListener {

    protected String make;
    protected String model;
    protected String year;
    protected String trim;

    Vehicle vehicle;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_vehicle_history);
        ButterKnife.bind(this);
        vehicle = getIntent().getExtras().getParcelable(Constants.VEHICLE_PARCEL_KEY);
        VehicleServiceFragment fragment = new VehicleServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.VEHICLE_PARCEL_KEY, vehicle);
        fragment.setArguments(bundle);
        addFragment(R.id.flContent,
                fragment,
                VehicleServiceFragment.FRAGMENT_TAG);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Garage");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }


    @Override
    public void onListFragmentInteraction(VehicleType item) {
        String selectedMake = item.getDisplayName();

        if(item.getType() < 3) {
            AddVehicleFragment modelFragment = new AddVehicleFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", item.getType() + 1);
            String valueString;
            switch (item.getType()) {
                case 0:
                    valueString = "make";
                    make = item.getDisplayName().concat(" ");
                    break;
                case 1:
                    valueString = "model";
                    model = item.getDisplayName().concat(" ");
                    break;
                case 2:
                    valueString = "year";
                    year = item.getDisplayName();
                    break;
                case 3:
                    valueString = "trim";
                    trim = item.getDisplayName();
                    break;
                default:
                    valueString = "";
                    break;
            }
            if (!TextUtils.isEmpty(valueString)) {
                bundle.putString(valueString, item.getId());
            }

            if(item.getType() == 3){
                ConfirmVehicleFragment confirmFragment = new ConfirmVehicleFragment();
                Bundle confirmBundle = new Bundle();
                confirmBundle.putString("year", this.year);
                confirmBundle.putString("make", this.make);
                confirmBundle.putString("model", this.model);
                confirmBundle.putString("trim", this.trim);
                confirmFragment.setArguments(confirmBundle);
                replaceFragment(R.id.flContent, confirmFragment, AddVehicleFragment.FRAGMENT_TAG, "confirmation");
            }
            else{
                modelFragment.setArguments(bundle);
                replaceFragment(R.id.flContent, modelFragment, AddVehicleFragment.FRAGMENT_TAG, valueString);
            }

        }

    }
}
