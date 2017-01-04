package com.caknow.android.customer.app.activity;


import android.os.Build;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.app.R;

/**
 * Created by junu on 12/31/16.
 */

public class SignUpActivity extends BaseActivity {
    @Override
    protected void initContentView() {
        setContentView(R.layout.sign_up_info_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.blue_title));
        }
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
}
