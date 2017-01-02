package com.caknow.android.customer.app.fragment.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.android.customer.app.activity.GarageActivity;
import com.caknow.android.customer.app.settings.SettingsActivity;
import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class SettingsFragment extends Fragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + SettingsFragment.class.getName();

    @OnClick(R.id.settings_layout_phone_layout)
    void openPhoneSetting(){
        UpdateSettingFragment updateFragment = new UpdateSettingFragment();
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, updateFragment, UpdateSettingFragment.FRAGMENT_TAG, "phone");
    }

    @OnClick(R.id.settings_layout_password_layout)
    void openPasswordSetting(){
        UpdateSettingFragment updateFragment = new UpdateSettingFragment();
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, updateFragment, UpdateSettingFragment.FRAGMENT_TAG, "password");
    }

    @OnClick(R.id.settings_layout_car_layout)
    void openGarage(){
        UpdateSettingFragment updateFragment = new UpdateSettingFragment();
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.startActivity(new Intent(settingsActivity, GarageActivity.class));
    }

    @OnClick(R.id.settings_sign_out_container)
    void signOut(){
        UpdateSettingFragment updateFragment = new UpdateSettingFragment();
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.finish();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("History");
    }
}
