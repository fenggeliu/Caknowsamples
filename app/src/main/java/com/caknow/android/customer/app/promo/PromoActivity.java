package com.caknow.android.customer.app.promo;

import android.widget.Button;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/2/2017.
 */

public class PromoActivity extends BaseActivity {

    @BindView(R.id.stel_back_btn)
    Button button;

    @OnClick(R.id.stel_back_btn)
    void close(){
        if(!PromoActivity.this.isFinishing()) PromoActivity.this.finish();
    }


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_promo);
        ButterKnife.bind(this);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch(Exception e){
            //
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
