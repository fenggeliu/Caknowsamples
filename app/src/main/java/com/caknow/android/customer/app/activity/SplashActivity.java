package com.caknow.android.customer.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.caknow.android.customer.app.BaseActivity;
import com.caknow.android.customer.app.home.HomeActivity;
import com.caknow.app.R;

/**
 * Created by wesson_wxy on 2016/12/5.
 */

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;    // delay time

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.splash_layout);}

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configView() {
        animate(findViewById(R.id.splash), 1000);
    }

    private class FadeInListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            route();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

    }

    private void animate(final View v, final long duration) {

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        anim.setAnimationListener(new FadeInListener());
        v.setAnimation(anim);

    }

    private void route() {
        boolean loggedIn = false;

        if (!loggedIn) {
            Intent intent = new Intent(this,
                    InitActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        SplashActivity.this.finish();
    }

}
