package com.caknow.android.customer.app.fragment.garage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.caknow.android.customer.app.activity.NewServiceRequestActivity;
import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class VehicleServiceFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + VehicleServiceFragment.class.getName();

    private String displayName;

    @BindView(R.id.vehicle_service_name)
    TextView vehicleName;

    @BindView(R.id.new_vehicle_service_button)
    Button submitButton;

    @OnClick(R.id.new_vehicle_service_button)
    void startNewService(){
        Intent intent = new Intent(getActivity(), NewServiceRequestActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        displayName = bundle.getString("displayName", "SOMETHING WENT WRONG");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vehicleservice, container, false);
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
