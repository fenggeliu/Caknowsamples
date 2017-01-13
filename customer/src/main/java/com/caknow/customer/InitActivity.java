package com.caknow.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.caknow.app.BuildConfig;
import com.caknow.app.R;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.registration.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

public class InitActivity extends BaseActivity {

    @BindView(R.id.init_layout_login_btn)
    Button signInButton;
    @BindView(R.id.init_layout_create_btn)
    Button createNewAcountButton;
    @BindView(R.id.init_logo)
    ImageView logo;

    // Start LoginActivity
    @OnClick(R.id.init_layout_login_btn)
    void startLogin(){
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    //Start SignUpActivity
    @OnClick(R.id.init_layout_create_btn)
    void startSignUp(){
        final Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    //TODO: REMOVE BEFORE PUBLISHING, used to enter mock app
    @OnClick(R.id.init_logo)
    void debugHome(){
        if(BuildConfig.DEBUG && !InitActivity.this.isFinishing()) {
            final Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            InitActivity.this.finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configView() {

    }

    @Override
    protected void setTitle() {

    }
}
