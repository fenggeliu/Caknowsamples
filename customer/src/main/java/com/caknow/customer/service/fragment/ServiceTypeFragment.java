package com.caknow.customer.service.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class ServiceTypeFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ServiceTypeFragment.class.getName();

    @BindView(R.id.rcll_grid_view)
    GridView repairLayout;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service_type, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


}