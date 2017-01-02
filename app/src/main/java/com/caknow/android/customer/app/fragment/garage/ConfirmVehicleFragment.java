package com.caknow.android.customer.app.fragment.garage;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class ConfirmVehicleFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ConfirmVehicleFragment.class.getName();

    private String displayName;

    @BindView(R.id.acsl_vehicle_name)
    TextView vehicleName;

    @BindView(R.id.acsl_submit_btn)
    Button submitButton;

    @OnClick(R.id.acsl_submit_btn)
    void addCarToGarage(){
        //TODO show loader
        //TODO build request
        //TODO add vehicle to local sqlite
        if(!getActivity().isFinishing()) getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        displayName = bundle.getString("displayName", "SOMETHING WENT WRONG");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_confirmvehicle, container, false);
        ButterKnife.bind(this, v);
        vehicleName.setText(displayName);
        return v;
    }

    //TODO DELETE

    private List<String> createDummyData(){
        List<String> strings = new ArrayList<>();
        strings.add("AM General");
        strings.add("Acura");
        strings.add("Alfa Romeo");
        strings.add("Aston Martin");
        strings.add("Audi");
        strings.add("Acura");
        strings.add("BMW");
        strings.add("Bentley");
        strings.add("Bugatti");
        strings.add("Chevrolet");
        return strings;
    }

}
