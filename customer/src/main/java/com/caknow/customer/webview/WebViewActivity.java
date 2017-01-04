package com.caknow.customer.webview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.widget.progressbar.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/2/2017.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.custom_webview) ProgressWebView webView;

    String urlToLoad;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        final Bundle extras = getIntent().getExtras();
        if(extras == null){
            urlToLoad = "http://www.caknow.com";
        } else {
            urlToLoad = extras.getString(Constants.URL_PARCEL_KEY, "http://www.caknow.com");
        }
        webView.loadUrl(urlToLoad);
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
