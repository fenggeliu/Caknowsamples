package com.caknow.customer.activity;


import android.content.Intent;
import android.os.Build;
import android.widget.EditText;

import com.caknow.customer.BaseActivity;
import com.caknow.app.R;
import com.caknow.customer.home.HomeActivity;

import java.util.List;

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

    @OnClick(R.id.suil_sign_up_btn)
    void executeSignUp(){
        registrationSuccess();
    }

    @OnClick(R.id.suil_button_close)
    void closeSignUp(){
        if(!SignUpActivity.this.isFinishing()){
            SignUpActivity.this.finish();
        }
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
