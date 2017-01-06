package com.caknow.customer.feedback;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class FeedbackFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + FeedbackFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_help, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("Help");
    }
}
