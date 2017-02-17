package com.caknow.customer.settings.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.util.net.settings.ConsumerInfoResponse;
import com.caknow.customer.util.net.settings.SettingsAPI;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.registration.InitActivity;
import com.caknow.customer.settings.SettingsActivity;
import com.caknow.customer.util.SessionPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class SettingsFragment extends BaseFragment {
    static final String TITLE_KEY = "title";
    static final String HINT_KEY = "hint";
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + SettingsFragment.class.getName();

    @BindView(R.id.settings_layout_username_text) TextView usernameContent;
    @BindView(R.id.settings_layout_photo_imageview) ImageView userPhotoImageView;

    @BindView(R.id.settings_layout_email_content) TextView emailContent;
    @BindView(R.id.settings_layout_phone_content) TextView phoneContentView;

    SettingsActivity settingsActivity;

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
        settingsActivity.replaceFragment(R.id.settingsContent, updatePasswordFragment, UpdatePasswordFragment.FRAGMENT_TAG, "password");
    }

    @OnClick(R.id.settings_layout_car_layout)
    void openGarage(){
        ManageCarFragment manageCarFragment = new ManageCarFragment();
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, manageCarFragment, ManageCarFragment.FRAGMENT_TAG, "manage_Car");
    }

    @OnClick(R.id.settings_sign_out_container)
    void signOut(){
        try {
            if (getActivity() != null && !getActivity().isFinishing()) {
                SessionPreferences.INSTANCE.resetCredentials();
                final Intent intent = new Intent(getActivity(), InitActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } catch (NullPointerException e){
            //
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, v);
        settingsActivity = (SettingsActivity) getActivity();
        SettingsAPI settingsAPI = retrofit.create(SettingsAPI.class);
        settingsAPI.getConsumerInfo().enqueue(new Callback<ConsumerInfoResponse>() {
            @Override
            public void onResponse(Call<ConsumerInfoResponse> call, Response<ConsumerInfoResponse> response) {
                ConsumerInfoResponse responseBody = response.body();
                emailContent.setText(responseBody.getPayload().getEmail());
                phoneContentView.setText(responseBody.getPayload().getPhone());
                usernameContent.setText(responseBody.getPayload().getFName() + " " + responseBody.getPayload().getLName());
                Glide.with(getContext()).load(responseBody.getPayload().getProfileImg()).into(userPhotoImageView);
            }

            @Override
            public void onFailure(Call<ConsumerInfoResponse> call, Throwable t) {

            }
        });


        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }


    @Override
    public void onResume(){
        ((SettingsActivity) getActivity()).updateTitle("Settings", R.drawable.ic_action_close);
        super.onResume();

    }


}
