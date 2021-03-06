package com.caknow.customer.registration;


import android.content.Intent;
import android.os.Build;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.constant.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.auth.AuthenticationPayload;
import com.caknow.customer.util.net.auth.AuthenticationResponse;
import com.caknow.customer.util.net.reg.RegistrationAPI;
import com.caknow.customer.util.net.reg.RegistrationRequest;
import com.caknow.customer.webview.WebViewActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by junu on 12/31/16.
 */

public class SignUpActivity extends BaseActivity implements Callback<AuthenticationResponse> {

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

    @Inject
    Retrofit retrofit;


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
        register();
    }


    @Override
    protected void initContentView() {
        setContentView(R.layout.sign_up_info_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.blue_title));
        }
        ButterKnife.bind(this);
        CAKNOWApplication.get().getNetComponent().inject(this);
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
//            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Sign Up");
        } catch (Exception e) {
            //
        }

    }

    private void register(){
        // prepare call in Retrofit 2.0
        RegistrationAPI registrationAPI = retrofit.create(RegistrationAPI.class);

        Call<AuthenticationResponse> call = registrationAPI.register(RegistrationRequest.getRequestBody(
                new RegistrationRequest(
                editTextList.get(2).getText().toString(),
                editTextList.get(4).getText().toString(),
                editTextList.get(0).getText().toString(),
                editTextList.get(1).getText().toString(),
                editTextList.get(3).getText().toString(),
                "")));
        //asynchronous call
        showProgress();
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<AuthenticationResponse> call, retrofit2.Response<AuthenticationResponse> response) {
        hideProgress();
        if (response.isSuccessful()) {
            Toast.makeText(SignUpActivity.this, "Success, log in now!", Toast.LENGTH_SHORT).show();
            this.finish();
        } else {
            Toast.makeText(SignUpActivity.this, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
        hideProgress();
        Toast.makeText(SignUpActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();

    }
}
