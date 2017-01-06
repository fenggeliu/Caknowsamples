package com.caknow.customer.history;

import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class HistoryActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

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
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("History");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }
}
