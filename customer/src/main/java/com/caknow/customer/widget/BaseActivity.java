package com.caknow.customer.widget;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.caknow.app.R;

import butterknife.ButterKnife;

/**
 * Created by wesson_wxy on 2016/12/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Bitmap mid;
    ProgressDialog progressDialog;

    /**
     * setContentView
     */
    protected abstract void initContentView();

    /**
     * initialize view
     */
    protected abstract void initView();

    /**
     * receive and send data
     */
    protected abstract void initData();

    /**
     * setting listener and adapter
     */
    protected abstract void configView();

    /**
     *
     */
    protected abstract void setTitle();

    public void showProgress() {
        try {
            if (progressDialog != null) {
                progressDialog.show();
            }
        } catch (Exception e) {

        }
    }

    public void hideProgress() {
        try {
            if (progressDialog != null) {
                progressDialog.hide();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.abs_layout);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button).setOnClickListener(view -> this.onBackPressed());
        } catch (Exception e) {
            //
        }
        //progressDialog.show();
        this.initContentView();
        this.initView();
        this.initData();
        this.configView();
        this.setTitle();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        //translucentBar(R.color.blue_title);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void translucentBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);

            View rectView = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            rectView.setLayoutParams(params);
            rectView.setBackgroundColor(getResources().getColor(color));

            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            decorView.addView(rectView);

            ViewGroup rootView = ButterKnife.findById(this, R.id.content);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    public void newFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(containerViewId)).commit();
        addFragment(containerViewId, fragment, fragmentTag);

    }

    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    public void replaceFragment(@IdRes int containerViewId,
                                @NonNull Fragment fragment,
                                @NonNull String fragmentTag,
                                @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}
