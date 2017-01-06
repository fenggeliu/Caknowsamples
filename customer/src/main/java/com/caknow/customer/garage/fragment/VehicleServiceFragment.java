package com.caknow.customer.garage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.garage.Vehicle;
import com.caknow.customer.service.NewServiceRequestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class VehicleServiceFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + VehicleServiceFragment.class.getName();

    private String displayName;

    @BindView(R.id.vehicle_service_name)
    TextView vehicleName;

    @BindView(R.id.srcl_car_logo_display)
    ImageView vehicleLogo;

    @BindView(R.id.new_vehicle_service_button)
    Button submitButton;

    @BindView(R.id.no_service_request)
    LinearLayout noServiceRequestLayout;


    @BindView(R.id.service_request_repair_list)
    ListView serviceRepairList;



    /**
     * Launches New Service Request
     */
    @OnClick(R.id.new_vehicle_service_button)
    void startNewService(){
        Intent intent = new Intent(getActivity(), NewServiceRequestActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve the vehicle data from the previous GarageFragment
        Bundle bundle = getArguments();
        Vehicle vehicle = bundle.getParcelable(Vehicle.PARCELABLE_KEY);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vehicleservice, container, false);
        unbinder = ButterKnife.bind(this, v);
        vehicleName.setText(vehicle.getName());
        vehicleLogo.setImageResource(R.drawable.alfa_romeo);
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
