package com.caknow.android.customer.app.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.caknow.android.customer.app.fragment.garage.AddVehicleFragment;
import com.caknow.android.customer.app.fragment.garage.ConfirmVehicleFragment;
import com.caknow.android.customer.app.fragment.garage.GarageFragment;
import com.caknow.android.customer.app.model.VehicleType;
import com.caknow.app.R;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class GarageActivity extends BaseActivity implements AddVehicleFragment.OnListFragmentInteractionListener {

    protected String make;
    protected String model;
    protected String year;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_garage);
        ButterKnife.bind(this);
        addFragment(R.id.flContent,
                new GarageFragment(),
                GarageFragment.FRAGMENT_TAG);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch(Exception e){
            //Log.e(e.getMessage());
        }
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

    public void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    public void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
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
