package com.caknow.customer.activity;


import android.content.Intent;
import android.os.Build;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.webview.WebViewActivity;

import java.util.List;

import butterknife.BindString;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 12/31/16.
 */

public class SignUpActivity extends BaseActivity {

    @BindViews({
            R.id.suil_first_name_input,
            R.id.suil_last_name_input,
            R.id.suil_email_input,
            R.id.suil_phone_input,
            R.id.suil_password_input
    })
    List<EditText> editTextList;

    @BindString(R.string.url_privacy_policy) String urlPrivacyPolicy;
    @BindString(R.string.url_terms_and_conditions) String urlTermsConditions;
    @BindString(R.string.url_terms_of_service) String urlTermsService;

    @OnClick({R.id.sign_up_privacy_policy_tv, R.id.sign_up_terms_and_conditions_tv, R.id.sign_up_terms_of_service_tv})
    void launchWebViewActivity(TextView view){
        final Intent webViewIntent = new Intent(this, WebViewActivity.class);
        String urlToLoad = "";
        switch(view.getId()){
            case R.id.sign_up_privacy_policy_tv:
                urlToLoad = urlPrivacyPolicy;
                break;
            case R.id.sign_up_terms_and_conditions_tv:
                urlToLoad = urlTermsConditions;
                break;
            case R.id.sign_up_terms_of_service_tv:
                urlToLoad = urlTermsService;
                break;
        }
        webViewIntent.putExtra(Constants.URL_PARCEL_KEY, urlToLoad);
    }

    @OnClick(R.id.suil_sign_up_btn)
    void executeSignUp(){
        registrationSuccess();
    }


    @Override
    protected void initContentView() {
        setContentView(R.layout.sign_up_info_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.blue_title));
        }
        ButterKnife.bind(this);

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
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Sign Up");
        } catch (Exception e) {
            //
        }

    }

    /**
     * This method handles the success event case of registration.
     */
    private void registrationSuccess(){
        final Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void registrationFailure(){

    }
}
