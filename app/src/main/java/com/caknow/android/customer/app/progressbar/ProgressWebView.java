package com.caknow.android.customer.app.progressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.caknow.app.R;

/**
 * Created by wesson_wxy on 2016/12/6.
 */

public class ProgressWebView extends WebView {
    private ProgressBar progressWebView;

    public ProgressWebView(Context context){
        super(context);
        init(context);
    }

    public void init(Context context){
        progressWebView = new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
        progressWebView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,6,0,0));
        progressWebView.setProgressDrawable(this.getResources().getDrawable(R.drawable.btn_progress_webview));
        addView(progressWebView);
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    public  ProgressWebView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient{
        @Override
        public void onProgressChanged(WebView view,int newProgress){
            if(newProgress == 100){
                progressWebView.setVisibility(View.GONE);
            } else {
                if(progressWebView.getVisibility() == GONE){
                    progressWebView.setVisibility(View.VISIBLE);
                }
                progressWebView.setProgress(newProgress);
            }
            super.onProgressChanged(view,newProgress);
        }
    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            if(url.startsWith("http://")|| url.startsWith("https://")){
                view.loadUrl(url);
            }
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public void onReceivedSslError(WebView view,
                                       SslErrorHandler handler, SslError error) {
            // TODO Auto-generated method stub
            handler.proceed();
        }
    }

    @Override
    protected void onScrollChanged(int l,int t, int oldl,int oldt){
        LayoutParams lp = (LayoutParams) progressWebView.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressWebView.setLayoutParams(lp);
        super.onScrollChanged(l,t,oldl,oldt);
    }
}
