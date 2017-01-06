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

public class NewServiceFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + NewServiceFragment.class.getName();

    //FIXME: group listener
    @BindView(R.id.nsr_repair_layout)
    LinearLayout repairLayout;
    @BindView(R.id.nsr_maintenance_layout)
    LinearLayout maintenanceLayout;
    @BindView(R.id.nsr_emergency_layout)
    LinearLayout emergencyLayout;

    @OnClick(R.id.nsr_repair_layout)
    void startRepair(){
        NewServiceRequestActivity homeActivity = (NewServiceRequestActivity) getActivity();
        ServiceTypeFragment fragment = new ServiceTypeFragment();
        fragment.setArguments(new Bundle());
        homeActivity.replaceFragment(R.id.flContent,
                new ServiceTypeFragment(),
                ServiceTypeFragment.FRAGMENT_TAG, "serviceTypeFragment");
    }

    @OnClick(R.id.nsr_maintenance_layout)
    void startMaintenance(){
        NewServiceRequestActivity homeActivity = (NewServiceRequestActivity) getActivity();
        ServiceTypeFragment fragment = new ServiceTypeFragment();
        fragment.setArguments(new Bundle());
        homeActivity.replaceFragment(R.id.flContent,
                new ServiceTypeFragment(),
                ServiceTypeFragment.FRAGMENT_TAG, "serviceTypeFragment");
    }

    @OnClick(R.id.nsr_emergency_layout)
    void startEmergency(){
        NewServiceRequestActivity homeActivity = (NewServiceRequestActivity) getActivity();
        ServiceTypeFragment fragment = new ServiceTypeFragment();
        fragment.setArguments(new Bundle());
        homeActivity.replaceFragment(R.id.flContent,
                new ServiceTypeFragment(),
                ServiceTypeFragment.FRAGMENT_TAG, "serviceTypeFragment");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_service, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


}
