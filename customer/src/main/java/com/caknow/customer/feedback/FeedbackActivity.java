package com.caknow.customer.feedback;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/2/2017.
 */

public class FeedbackActivity extends BaseActivity {

    @OnClick(R.id.phone_number_display)
    void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.help_layout_description_phone_number)));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            }
            return;
        }
        FeedbackActivity.this.startActivity(intent);
    }
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_feedback);
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

            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Help");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }
}
