package com.caknow.customer.quote;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.caknow.customer.BaseActivity;
import com.caknow.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class QuoteActivity extends BaseActivity {

    @BindView(R.id.topContent)
    FrameLayout topLayout;

    @BindView(R.id.bottomContent)
    FrameLayout bottomLayout;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_quote);
        ButterKnife.bind(this);
        addFragment(R.id.topContent,
                new QuoteMapFragment(),
                QuoteMapFragment.FRAGMENT_TAG);
        addFragment(R.id.bottomContent,
                new QuoteDetailFragment(),
                QuoteDetailFragment.FRAGMENT_TAG);
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


    /**
     * Use this to hide a fragment from view
     * @param fragment
     */
    private void hideFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
    }
}
