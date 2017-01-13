package com.caknow.customer.registration;


import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
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
import com.caknow.customer.util.net.HeadersContract;
import com.caknow.customer.util.net.reg.RegistrationAPI;
import com.caknow.customer.util.net.reg.VerificationPayload;
import com.caknow.customer.util.net.reg.VerificationRequest;
import com.caknow.customer.util.net.reg.VerificationResponse;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;
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

public class VerificationActivity extends BaseActivity implements Callback<VerificationResponse> {

    @BindViews({
            R.id.verification_code_digit_1,
            R.id.verification_code_digit_2,
            R.id.verification_code_digit_3,
            R.id.verification_code_digit_4
    })
    List<EditText> editTextList;

    @BindView(R.id.verification_submit_button)
    Button submitButton;
    private OkHttpClient client;
    private Retrofit retrofit;

    @OnClick(R.id.verification_submit_button)
    void verifyAccount() {
        StringBuilder sb = new StringBuilder();
        sb.append(editTextList.get(0).getText().toString());
        sb.append(editTextList.get(1).getText().toString());
        sb.append(editTextList.get(2).getText().toString());
        sb.append(editTextList.get(3).getText().toString());
        final String verificationInput = sb.toString();
        if (verificationInput.length() == 4) {
            validate(verificationInput);
        }

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_verification);
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
                        .header(HeadersContract.HEADER_X_API_KEY, Constants.apiKey) // <-- this is the important line
                        .header("Content-Type", "application/json")
                        .header(HeadersContract.HEADER_X_ACCESS_TOKEN, SessionPreferences.INSTANCE.getStringPref(PreferenceKeys.ACCESS_TOKEN));
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
            ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setVisibility(View.GONE);
            ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Verify Account");
        } catch (Exception e) {
            //
        }

    }

    private void validate(String code) {
        // prepare call in Retrofit 2.0
        RegistrationAPI registrationAPI = retrofit.create(RegistrationAPI.class);
        String text = VerificationRequest.getJsonString(
                new VerificationRequest(code));
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), text);

        Call<VerificationResponse> call = registrationAPI.verify(body);
        //asynchronous call
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<VerificationResponse> call, retrofit2.Response<VerificationResponse> response) {
        if (response.isSuccessful()) {
            Toast.makeText(VerificationActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            VerificationPayload verificationPayload = response.body().getPayload();

            SessionPreferences.INSTANCE.setBoolPref(PreferenceKeys.BOOL_VERIFICATION_STATUS, verificationPayload.isVerified());

            final Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onFailure(Call<VerificationResponse> call, Throwable t) {

        Toast.makeText(VerificationActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();

    }
}
