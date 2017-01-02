package com.caknow.android.customer.app.activity;

import com.caknow.android.customer.app.fragment.payment.PaymentFragment;
import com.caknow.android.customer.app.fragment.payment.PaymentMethodFragment;
import com.caknow.android.customer.app.model.Payment;
import com.caknow.app.R;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class PaymentActivity extends BaseActivity implements PaymentMethodFragment.OnListFragmentInteractionListener{


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch(Exception e){
            //
        }
        addFragment(R.id.flContent, new PaymentMethodFragment(), PaymentMethodFragment.FRAGMENT_TAG);
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
    public void onListFragmentInteraction(Payment item) {

    }
}
