package com.caknow.customer.promo;

import android.widget.ImageButton;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;

/**
 * Created by junu on 1/2/2017.
 */

public class PromoActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_promo);
        addFragment(R.id.promoContent, new PromoFragment(), PromoFragment.FRAGMENT_TAG);
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
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Share To Earn");
            ((ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }
}
