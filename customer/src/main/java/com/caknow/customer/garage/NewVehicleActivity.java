package com.caknow.customer.garage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.garage.fragment.AddVehicleFragment;
import com.caknow.customer.garage.fragment.ConfirmVehicleFragment;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class NewVehicleActivity extends BaseActivity implements AddVehicleFragment.OnListFragmentInteractionListener {

    protected String make;
    protected String model;
    protected String year;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_garage);
        ButterKnife.bind(this);


        AddVehicleFragment fragment = new AddVehicleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        fragment.setArguments(bundle);
        addFragment(R.id.flContent,
                fragment,
                AddVehicleFragment.FRAGMENT_TAG);

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
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("New Vehicle");
            ((ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
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
                default:
                    valueString = "";
                    break;
            }
            if (!TextUtils.isEmpty(valueString)) {
                bundle.putString(valueString, item.getId());
            }

            if(item.getType() == 2){
                ConfirmVehicleFragment confirmFragment = new ConfirmVehicleFragment();
                Bundle confirmBundle = new Bundle();
                confirmBundle.putString("displayName", this.make.concat(this.model).concat(this.year));
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