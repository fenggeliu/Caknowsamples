package com.caknow.customer.message;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class MessagesActivity extends BaseActivity {

    @BindView(R.id.messages_activity_container)
    FrameLayout content;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        addFragment(R.id.messages_activity_container, new MessageFragment(), MessageFragment.FRAGMENT_TAG);
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
            ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Messages");
            ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e) {
            //
        }
    }
}
