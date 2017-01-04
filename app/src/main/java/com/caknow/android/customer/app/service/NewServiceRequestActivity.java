package com.caknow.android.customer.app.service;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.app.R;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceRequestActivity extends BaseActivity {


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_service_request);
        ButterKnife.bind(this);
        addFragment(R.id.flContent,
                new NewServiceFragment(),
                NewServiceFragment.FRAGMENT_TAG);
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
