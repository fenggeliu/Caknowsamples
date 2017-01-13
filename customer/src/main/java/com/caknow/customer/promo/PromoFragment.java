package com.caknow.customer.promo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.webview.WebViewActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class PromoFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + PromoFragment.class.getName();

    @BindString(R.string.url_terms_and_conditions) String termsUrl;

    @BindView(R.id.promo_terms_and_conditions_tv)
    TextView termsLink;

    @OnClick(R.id.promo_terms_and_conditions_tv)
    void openTermsAndConditions(){

        final Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(Constants.URL_PARCEL_KEY, termsUrl);
        startActivity(intent);
    }

    @OnClick(R.id.promo_code_email_button)
    void startEmailIntent(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_promo_bak, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }
}
