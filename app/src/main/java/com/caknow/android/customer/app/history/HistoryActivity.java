package com.caknow.android.customer.app.history;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.app.R;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class HistoryActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_history);
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
