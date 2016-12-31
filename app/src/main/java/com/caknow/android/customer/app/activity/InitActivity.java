package com.caknow.android.customer.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.caknow.app.R;

import butterknife.BindView;
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

    /**
     * Butterknife OnClick methods
     */
    @OnClick(R.id.init_layout_login_btn)
    private void startLogin(){
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.init_layout_create_btn)
    private void startSignUp(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_layout);

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
