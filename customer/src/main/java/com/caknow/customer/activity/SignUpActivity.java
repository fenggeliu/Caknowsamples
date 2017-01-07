package com.caknow.customer.activity;


import android.content.Intent;
import android.os.Build;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.auth.AuthenticationAPI;
import com.caknow.customer.util.net.auth.AuthenticationPayload;
import com.caknow.customer.util.net.auth.AuthenticationResponse;
import com.caknow.customer.util.net.content.LoginRequestPayload;
import com.caknow.customer.util.net.reg.RegistrationAPI;
import com.caknow.customer.util.net.reg.RegistrationRequest;
import com.caknow.customer.webview.WebViewActivity;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindString;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


    private OkHttpClient client;
    private Retrofit retrofit;

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

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (client == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addNetworkInterceptor(new StethoInterceptor());
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("x-api-key", "sJvVmx9uyJD7eE1bZraPEUfsm6BpzyOlgDZ04eqRyUs=") // <-- this is the important line
                        .header("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
            client = httpClient.build();
        }
        if (retrofit == null) {
            Gson gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

        }
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

    private void register(){
        // prepare call in Retrofit 2.0
        RegistrationAPI registrationAPI = retrofit.create(RegistrationAPI.class);
        String text = RegistrationRequest.getJsonString(
                new RegistrationRequest(
                        editTextList.get(2).getText().toString(),
                        editTextList.get(4).getText().toString(),
                        editTextList.get(0).getText().toString(),
                        editTextList.get(1).getText().toString(),
                        editTextList.get(3).getText().toString(),
                        "refCode"));
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), text);

        Call<AuthenticationResponse> call = registrationAPI.register(body);
        //asynchronous call
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<AuthenticationResponse> call, retrofit2.Response<AuthenticationResponse> response) {
        if (response.isSuccessful()) {
            Toast.makeText(SignUpActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            AuthenticationPayload authPayload = response.body().getAuthenticationPayload();

            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.ACCESS_TOKEN, authPayload.getToken());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.USER_FNAME, authPayload.getfName());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.USER_ID, authPayload.get_id());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.REFRESH_TOKEN, authPayload.getRefreshToken());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.USER_VERIFICATION_STATUS, authPayload.getVerificationStatus());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.STRIPE_TOKEN, authPayload.getStripeCusToken());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.PUBNUB_CHANNEL, authPayload.getPubnubChnl());

            final Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {

        Toast.makeText(SignUpActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();

    }
}
