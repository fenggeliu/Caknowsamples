package com.caknow.customer.quote;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import com.caknow.customer.BaseFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.app.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by junu on 1/1/17.
 */

public class QuoteDetailFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + QuoteDetailFragment.class.getName();
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_history, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("Quotes");
    }

}
