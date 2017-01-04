package com.caknow.customer.service;

import com.caknow.customer.BaseActivity;
import com.caknow.app.R;
import com.caknow.customer.service.fragment.NewServiceFragment;
import com.caknow.customer.service.fragment.ServiceLocationFragment;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceRequestActivity extends BaseActivity {



    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_service_request);
        ButterKnife.bind(this);
        addFragment(R.id.flContent,
                new ServiceLocationFragment(),
                ServiceLocationFragment.FRAGMENT_TAG);
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
