package com.caknow.android.customer.app.garage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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

public class GarageFragment extends Fragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + GarageFragment.class.getName();

    @BindView(R.id.add_car_btn)
    TextView addCarButton;

    @BindView(R.id.home_empty_garage_view)
    LinearLayout emptyGarageView;

    @BindView(R.id.vehicle_display)
    GridView vehicleGridView;

    @OnClick(R.id.add_car_btn)
    void addNewCar(){

        Intent intent = new Intent(getActivity(), NewVehicleActivity.class);
        startActivity(intent);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_garage, container, false);
        ButterKnife.bind(this, v);

        // TODO: If garage has cars, hide addNewCar button
         emptyGarageView.setVisibility(View.GONE);
        ListAdapter gridViewAdapter = new GarageAdapter(getContext(), createDummyData());
        vehicleGridView.setAdapter(gridViewAdapter);
        vehicleGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VehicleServiceFragment vehicleServiceFragment = new VehicleServiceFragment();
                Vehicle vehicle = new Vehicle("01", "Super Duper Fast Car", "www.google.com");
                Bundle bundle = new Bundle();
                bundle.putParcelable(Vehicle.PARCELABLE_KEY, vehicle);
                vehicleServiceFragment.setArguments(bundle);

                GarageActivity activity = (GarageActivity) getActivity();
                activity.replaceFragment(R.id.flContent, vehicleServiceFragment, VehicleServiceFragment.FRAGMENT_TAG, "vehicle");
            }
        });
        return v;
    }

    private List<Vehicle> createDummyData(){
        List<Vehicle> dummyData = new ArrayList<>();
        dummyData.add(new Vehicle("0001", "car1", "www.google.com"));
        dummyData.add(new Vehicle("0002", "car2", "www.google.com"));
        dummyData.add(new Vehicle("0003", "car3", "www.google.com"));
        dummyData.add(new Vehicle("0004", "car4", "www.google.com"));
        return dummyData;
    }
}
