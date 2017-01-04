package com.caknow.customer.message;

import android.widget.FrameLayout;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class MessageActivity extends BaseActivity {

    @BindView(R.id.messages_content_display)
    FrameLayout content;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch(Exception e){
            //
        }
        addFragment(R.id.messages_content_display, new MessageFragment(), MessageFragment.FRAGMENT_TAG);
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
