package com.caknow.customer.garage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.garage.GarageActivity;
import com.caknow.customer.garage.NewVehicleActivity;
import com.caknow.customer.garage.Vehicle;
import com.caknow.customer.garage.adapter.GarageAdapter;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.net.AuthenticationAPI;
import com.caknow.customer.util.net.AuthenticationResponse;
import com.caknow.customer.util.net.content.LoginRequestPayload;
import com.caknow.customer.util.net.garage.GarageAPI;
import com.caknow.customer.util.net.garage.GarageResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class GarageFragment extends BaseFragment implements Callback<GarageResponse> {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + GarageFragment.class.getName();


    @BindView(R.id.home_empty_garage_view)
    LinearLayout emptyGarageView;

    @BindView(R.id.vehicle_display)
    GridView vehicleGridView;

    HomeActivity homeActivity;

    ListAdapter gridViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_garage, container, false);
        unbinder = ButterKnife.bind(this, v);
        homeActivity = (HomeActivity) getActivity();
        loadData();

        return v;
    }

    private void loadData(){
        // prepare call in Retrofit 2.0
        if(homeActivity != null) {
            GarageAPI garageAPI = homeActivity.retrofit.create(GarageAPI.class);
            Call<GarageResponse> call = garageAPI.getVehicles();
            //asynchronous call
            call.enqueue(this);

            // synchronous call would be with execute, in this case you
            // would have to perform this outside the main thread
//         call.execute();

            // to cancel a running request
            // call.cancel();
            // calls can only be used once but you can easily clone them
            //Call<StackOverflowQuestions> c = call.clone();
            //c.enqueue(this);
        }
    }

    private List<Vehicle> createDummyData(){
        List<Vehicle> dummyData = new ArrayList<>();
        dummyData.add(new Vehicle("0001", "car1", "www.google.com"));
        dummyData.add(new Vehicle("0002", "car2", "www.google.com"));
        dummyData.add(new Vehicle("0003", "car3", "www.google.com"));
        dummyData.add(new Vehicle("0004", "car4", "www.google.com"));
        return dummyData;
    }

    @Override
    public void onResponse(Call<GarageResponse> call, Response<GarageResponse> response) {

        List<Vehicle> vehicles;
        try{
            emptyGarageView.setVisibility(View.GONE);
            vehicles = response.body().getGaragePayload().getVehicles();
            emptyGarageView.setVisibility(View.GONE);
            gridViewAdapter = new GarageAdapter(getContext(), vehicles);
            vehicleGridView.setAdapter(gridViewAdapter);
            vehicleGridView.setOnItemClickListener((adapterView, view, i, l) -> {
                VehicleServiceFragment vehicleServiceFragment = new VehicleServiceFragment();
                Vehicle vehicle = new Vehicle("01", "Super Duper Fast Car", "www.google.com");
                Bundle bundle = new Bundle();
                bundle.putParcelable(Vehicle.PARCELABLE_KEY, vehicle);
                vehicleServiceFragment.setArguments(bundle);

                HomeActivity activity = (HomeActivity) getActivity();
                activity.replaceFragment(R.id.flContent, vehicleServiceFragment, VehicleServiceFragment.FRAGMENT_TAG, "vehicle");
            });
        } catch (Exception e){
            //
        }


    }

    @Override
    public void onFailure(Call<GarageResponse> call, Throwable t) {
        //Show Error
    }
}
