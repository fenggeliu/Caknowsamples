package com.caknow.android.customer.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.android.customer.app.home.HomeActivity;
import com.caknow.app.BuildConfig;
import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

public class InitActivity extends BaseActivity {

    /**
     * Constants
     */


    /**
     * Views
     */
    @BindView(R.id.init_layout_login_btn)
    Button signInButton;
    @BindView(R.id.init_layout_create_btn)
    Button createNewAcountButton;
    @BindView(R.id.init_logo)
    ImageView logo;

    /**
     * Butterknife OnClick methods
     */
    @OnClick(R.id.init_layout_login_btn)
    void startLogin(){
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.init_layout_create_btn)
    void startSignUp(){
        final Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.init_logo)
    void debugHome(){
        if(BuildConfig.DEBUG && !InitActivity.this.isFinishing()) {
            final Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            InitActivity.this.finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.init_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void initContentView() {

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
}
