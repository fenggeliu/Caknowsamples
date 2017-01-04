package com.caknow.customer.settings.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import com.caknow.customer.BaseFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.customer.garage.GarageActivity;
import com.caknow.app.R;
import com.caknow.customer.settings.SettingsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class SettingsFragment extends BaseFragment {
    static final String TITLE_KEY = "title";
    static final String HINT_KEY = "hint";

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + SettingsFragment.class.getName();

    @OnClick(R.id.settings_layout_phone_layout)
    void openPhoneSetting(){
        UpdateSettingFragment updateFragment = new UpdateSettingFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, "Phone");
        bundle.putString(HINT_KEY, "Enter your phone number");
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, updateFragment, UpdateSettingFragment.FRAGMENT_TAG, "phone");
    }

    @OnClick(R.id.settings_layout_password_layout)
    void openPasswordSetting(){
        UpdatePasswordFragment updatePasswordFragment = new UpdatePasswordFragment();
        final Bundle bundle = new Bundle();

        bundle.putString(TITLE_KEY, "Change Password");
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, updatePasswordFragment, UpdateSettingFragment.FRAGMENT_TAG, "password");
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
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("History");
    }
}
