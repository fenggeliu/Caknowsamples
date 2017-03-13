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
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.constant.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.net.reg.RegistrationAPI;
import com.caknow.customer.util.net.reg.VerificationPayload;
import com.caknow.customer.util.net.reg.VerificationRequest;
import com.caknow.customer.util.net.reg.VerificationResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    @BindView(R.id.resend_code_tv)
    TextView resentCode;

    @OnClick(R.id.verification_submit_button)
    void verifyAccount(){
        StringBuilder sb = new StringBuilder();
        sb.append(editTextList.get(0).getText().toString());
        sb.append(editTextList.get(1).getText().toString());
        sb.append(editTextList.get(2).getText().toString());
        sb.append(editTextList.get(3).getText().toString());
        final String verificationInput = sb.toString();
        if(verificationInput.length() == 4){
            validate(verificationInput);
        }else{
            Toast.makeText(VerificationActivity.this, "Wrong input", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.resend_code_tv)
    void resentCode(){
        RegistrationAPI registrationAPI = retrofit.create(RegistrationAPI.class);
        registrationAPI.resendCode().enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(VerificationActivity.this, "New code sent", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }

   @Inject Retrofit retrofit;



    @Override
    protected void initContentView() {
        CAKNOWApplication.get().getNetComponent().inject(this);
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

    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setVisibility(View.GONE);
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Verify Account");
        } catch (Exception e) {
            //
        }

    }

    private void validate(String code){
        // prepare call in Retrofit 2.0
        RegistrationAPI registrationAPI = retrofit.create(RegistrationAPI.class);
        String text = VerificationRequest.getJsonString(
               new VerificationRequest(code));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), text);

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
