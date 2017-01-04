package com.caknow.android.customer.app.feedback;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.app.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/2/2017.
 */

public class FeedbackActivity extends BaseActivity {


    @OnClick(R.id.help_layout_back_btn)
    void close() {
        if (!FeedbackActivity.this.isFinishing()) {
            FeedbackActivity.this.finish();
        }
    }

    @OnClick(R.id.help_submit_button)
    void submit() {

    }

    @OnClick(R.id.phone_number_display)
    void call() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getString(R.string.help_layout_description_phone_number)));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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
}
