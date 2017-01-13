package com.caknow.customer.payment;

import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class PaymentActivity extends BaseActivity implements PaymentMethodFragment.OnListFragmentInteractionListener{


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        addFragment(R.id.paymentContent, new PaymentMethodFragment(), PaymentMethodFragment.FRAGMENT_TAG);
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
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Credit Cards");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public void onListFragmentInteraction(Payment item) {

    }
}
