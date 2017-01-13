package com.caknow.customer.webview;

import android.os.Bundle;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.widget.progressbar.ProgressWebView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/2/2017.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.custom_webview)
    ProgressWebView webView;
    @BindString(R.string.url_default)
    String defaultUrl;
    String urlToLoad;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        final Bundle extras = getIntent().getExtras();
        if (extras == null) {
            urlToLoad = defaultUrl;
        } else {
            urlToLoad = extras.getString(Constants.URL_PARCEL_KEY, defaultUrl);
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

    @Override
    protected void setTitle() {
        try {

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("About Us");
        } catch (NullPointerException e) {
            //
        }
    }
}
