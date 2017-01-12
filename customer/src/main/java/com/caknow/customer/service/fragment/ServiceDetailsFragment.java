package com.caknow.customer.service.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.service.NewServiceRequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class ServiceDetailsFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ServiceDetailsFragment.class.getName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service_request_detail, container, false);
        unbinder = ButterKnife.bind(this, v);
        ((NewServiceRequestActivity)getActivity()).updateTitle("Details", R.drawable.ic_action_back);
        return v;
    }


}
